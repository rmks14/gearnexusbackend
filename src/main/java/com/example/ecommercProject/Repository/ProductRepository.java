package com.example.ecommercProject.Repository;//package com.example.ecommercProject.Repository;
//
//import com.example.ecommercProject.model.Product;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findByCategory(String category);
//    List<Product> findBySellerId(Long sellerId);
//    List<Product> findByBuyerId(Long buyerId);
//}
//



import com.example.ecommercProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // ✅ Fetch products by category
    List<Product> findByCategory(String category);

    // ✅ Fetch all products sold by a specific user
    List<Product> findBySellerId(Long sellerId);

    // ✅ Fetch all products bought by a specific user
    List<Product> findByBuyerId(Long buyerId);

    // ✅ Fetch all available (unsold) products
    List<Product> findByBuyerIsNull();
}
