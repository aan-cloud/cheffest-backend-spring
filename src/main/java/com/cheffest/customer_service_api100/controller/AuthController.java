package com.cheffest.customer_service_api100.controller;

import com.cheffest.customer_service_api100.model.Cart;
import com.cheffest.customer_service_api100.model.User;
import com.cheffest.customer_service_api100.repository.CartRepository;
import com.cheffest.customer_service_api100.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final JwtDecoder jwtDecoder;

    public AuthController(UserRepository userRepository, CartRepository cartRepository, JwtDecoder jwtDecoder) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.jwtDecoder = jwtDecoder;
    }

    @PostMapping("/google")
    public ResponseEntity<?> authenticateWithGoogle(@RequestBody Map<String, String> requestBody) {
        String jwtToken = requestBody.get("token");

        if (jwtToken == null || jwtToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Token is required");
        }

        try {
            // Decode JWT dari Google
            Jwt jwt = jwtDecoder.decode(jwtToken);
            String email = jwt.getClaimAsString("email");

            // Cek apakah user sudah terdaftar
            Optional<User> existingUser = userRepository.findByEmail(email);
            User user;
            if (existingUser.isPresent()) {
                user = existingUser.get();
            } else {
                // Jika user belum ada, buat user baru
                user = new User();
                user.setEmail(email);
                user.setName(jwt.getClaimAsString("name"));
                user.setAvatarUrl(jwt.getClaimAsString("picture"));
                userRepository.save(user);
            }

            // Cek apakah user sudah memiliki cart, jika belum buatkan cart
            if (user.getCart() == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                cartRepository.save(newCart);

                user.setCart(newCart); // Hubungkan cart ke user
                userRepository.save(user); // Simpan kembali user dengan cart yang terhubung
            }

            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "token", jwtToken
            ));

        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }



    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized - Missing Token"));
        }

        String token = authHeader.substring(7);

        try {
            System.out.println("Token received: " + token); // Debug token

            Jwt jwt = this.jwtDecoder.decode(token);
            String email = jwt.getClaimAsString("email");

            System.out.println("Extracted email: " + email); // Debug email

            Optional<User> user = userRepository.findByEmail(email);

            if (user.isEmpty()) {
                System.out.println("User not found in database"); // Debug user not found
                return ResponseEntity.status(404).body(Map.of("error", "User not found"));
            }

            return ResponseEntity.ok(Map.of("userData", user.get()));

        } catch (Exception e) {
            e.printStackTrace(); // Cetak error di log
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Internal Server Error",
                    "message", e.getMessage()
            ));
        }
    }
}
