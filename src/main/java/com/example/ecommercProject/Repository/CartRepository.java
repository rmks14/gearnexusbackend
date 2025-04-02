package com.example.ecommercProject.Repository;

import com.example.ecommercProject.model.Cart;
import com.example.ecommercProject.model.Product;
import com.example.ecommercProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(Long userId);

    Optional<Cart> findByUserAndProduct(User user, Product product);

    void deleteByUserId(Long userId); // Clear all cart items for a user
}
