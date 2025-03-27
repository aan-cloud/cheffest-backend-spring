package com.cheffest.customer_service_api100.repository;

import com.cheffest.customer_service_api100.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FoodRepository extends JpaRepository<Food, UUID> {
    Optional<Food> findBySlug(String slug);
}
