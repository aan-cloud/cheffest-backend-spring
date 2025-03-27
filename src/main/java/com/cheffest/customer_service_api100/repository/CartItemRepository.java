package com.cheffest.customer_service_api100.repository;

import com.cheffest.customer_service_api100.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    Optional<CartItem> findByCartIdAndFoodId(UUID cartId, UUID id);

    List<CartItem> findByCartId(UUID id);
}
