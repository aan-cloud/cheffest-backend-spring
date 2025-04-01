package com.cheffest.customer_service_api100.controller;

import com.cheffest.customer_service_api100.model.Cart;
import com.cheffest.customer_service_api100.model.CartItem;
import com.cheffest.customer_service_api100.model.Food;
import com.cheffest.customer_service_api100.model.User;
import com.cheffest.customer_service_api100.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.cheffest.customer_service_api100.repository.UserRepository;
import com.cheffest.customer_service_api100.repository.FoodRepository;
import com.cheffest.customer_service_api100.repository.CartRepository;
import com.cheffest.customer_service_api100.repository.CartItemRepository;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<?> postFoodToCart(@RequestBody CartRequest cartRequest) {
        Optional<User> existingUserOpt = userRepository.findById(cartRequest.getUserId());
        Optional<Food> foodOpt = foodRepository.findById(cartRequest.getFoodId());

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "You're not authorized for this content, please login first!"));
        }
        if (foodOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Product not found!"));
        }

        User user = existingUserOpt.get();
        Food food = foodOpt.get();

        // Pastikan user memiliki cart, kalau tidak, buat baru
        Cart cart = cartRepository.findByUserId(user.getId()).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        Optional<CartItem> existingFoodInCart = cartItemRepository.findByCartIdAndFoodId(cart.getId(), food.getId());

        if (existingFoodInCart.isEmpty()) {
            // Tambahkan item baru ke keranjang
            CartItem newCartItem = new CartItem();
            newCartItem.setFood(food);
            newCartItem.setCart(cart);
            newCartItem.setQuantity(cartRequest.getSum());

            cartItemRepository.save(newCartItem);

            return ResponseEntity.ok(Map.of(
                    "message", "Success add product to cart",
                    "productName", food.getName(),
                    "quantity", cartRequest.getSum()
            ));
        }

        // Jika sudah ada, update quantity
        CartItem existingItem = existingFoodInCart.get();
        existingItem.setQuantity(existingItem.getQuantity() + cartRequest.getSum());
        cartItemRepository.save(existingItem);

        return ResponseEntity.ok(Map.of(
                "message", "Success update quantity product to cart",
                "productName", existingItem.getFood().getName(),
                "quantity", existingItem.getQuantity()
        ));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserCart(@PathVariable UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isEmpty()) {
            return ResponseEntity.ok(Map.of("totalPrice", BigDecimal.ZERO, "items", Collections.emptyList()));
        }

        List<CartItem> items = cartItemRepository.findByCartId(cart.get().getId());
        BigDecimal totalPrice = items.stream()
                .map(item -> item.getFood().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ResponseEntity.ok(Map.of(
                "totalPrice", totalPrice,
                "cart", cart.get(),
                "items", items
        ));
    }

    @DeleteMapping("/delete/{cartItemId}/{slug}")
    public ResponseEntity<?> deleteCartItem(@PathVariable UUID cartItemId, @PathVariable String slug) {
        Optional<Food> product = foodRepository.findBySlug(slug);
        if (product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        Optional<CartItem> cartItem = cartItemRepository.findById(Set.of(cartItemId).toString());
        if (cartItem.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found");
        }

        cartItemRepository.delete(cartItem.get());

        return ResponseEntity.ok(Map.of(
                "message", "Success delete product",
                "productName", product.get().getName()
        ));
    }
}

