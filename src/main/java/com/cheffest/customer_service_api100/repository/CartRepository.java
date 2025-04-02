package com.cheffest.customer_service_api100.repository;

import com.cheffest.customer_service_api100.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByUserId(UUID id);

    @Query("SELECT c FROM Cart c " +
            "LEFT JOIN FETCH c.items i " +
            "LEFT JOIN FETCH i.food f " +
            "WHERE c.user.id = :userId")
    Optional<Cart> findByUserIdWithItems(UUID userId);
}
