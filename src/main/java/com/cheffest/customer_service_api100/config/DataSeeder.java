package com.cheffest.customer_service_api100.config;

import com.cheffest.customer_service_api100.model.Food;
import com.cheffest.customer_service_api100.repository.FoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedDatabase(FoodRepository foodRepository) {
        return args -> {
            if (foodRepository.count() == 0) {
                List<Food> foods = List.of(
                        new Food("Pasta", "pasta", new BigDecimal("7.49"), "Creamy Alfredo pasta", "https://example.com/pasta.jpg"),
                        new Food("Naan Burger", "naan-burger", new BigDecimal("1.85"), "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "https://ucarecdn.com/62be9b1d-2221-45b0-89b3-7c9f406c2f28/image1101.png"),
                        new Food("Butter Chicken Taco", "butter-chicken-taco", new BigDecimal("1.15"), "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "https://ucarecdn.com/d2936e25-ee2e-4c1a-ae0f-294f3a445ea0/wepikexport20231110201118IYwl2.png"),
                        new Food("Chicken Burger", "chicken-burger", new BigDecimal("2.01"), "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "https://ucarecdn.com/06251df7-5498-4a06-b047-22c0df8aa07e/Image.png"),
                        new Food("Cheese Chicken Naan", "cheese-chicken-naan", new BigDecimal("2.50"), "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "https://ucarecdn.com/e37743ec-91d1-451c-a1c8-90e936c4c8fc/image1100.png"),
                        new Food("Three Layer Burger", "three-layer-burger", new BigDecimal("4.99"), "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "https://ucarecdn.com/52ee91db-dfee-4a90-82eb-6cfb08dec0f7/Image1.png"),
                        new Food("Sandwich", "sandwich", new BigDecimal("2.80"), "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "https://ucarecdn.com/88ecd56e-b12d-402c-abd5-33c390afec83/Image2.png")
                );

                foodRepository.saveAllAndFlush(foods);
                System.out.println("Database has been seeded!");
            }
        };
    }
}
