package com.cheffest.customer_service_api100.controller;

import com.cheffest.customer_service_api100.model.Food;
import com.cheffest.customer_service_api100.repository.FoodRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/foods")
public class FoodController {

    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Food> getFoodById(@PathVariable UUID id) {
        return foodRepository.findAllById(Collections.singleton(id));
    }
}
