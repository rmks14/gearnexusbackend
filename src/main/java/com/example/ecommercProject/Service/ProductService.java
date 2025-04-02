package com.example.ecommercProject.Service;

import com.example.ecommercProject.Repository.ProductRepository;
import com.example.ecommercProject.Repository.UserRepository;
import com.example.ecommercProject.model.Product;
import com.example.ecommercProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Fetch all available products (Only unsold ones)
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getBuyer() == null)  // Only show unsold products
                .toList();
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Add a new product to sell
//    public Product addProduct(Product product, MultipartFile file) {
//        try {
//            product.setImageData(file.getBytes()); // ✅ Properly handling image data
//        } catch (IOException | java.io.IOException e) {
//            throw new RuntimeException("Error processing image", e); // ✅ Convert to RuntimeException
//        }
//        return productRepository.save(product);
//    }
    public Product addProduct(Long userId,Product product,MultipartFile file) throws IOException {
        User seller = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        product.setSeller(seller);
        product.setImageData(file.getBytes());

        return productRepository.save(product);
    }


    // Delete product (Only if it exists)
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Buy a product (Assign buyer & remove from marketplace)
    public Optional<Product> purchaseProduct(Long productId, Long buyerId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        Optional<User> buyerOptional = userRepository.findById(buyerId);

        if (productOptional.isPresent() && buyerOptional.isPresent()) {
            Product product = productOptional.get();
            User buyer = buyerOptional.get();

            if (product.getBuyer() == null) { // Ensure it's not already sold
                product.setBuyer(buyer);
                productRepository.save(product);
                return Optional.of(product);
            }
        }
        return Optional.empty(); // Purchase failed
    }

    // Get all products sold by a user
    public List<Product> getProductsSoldByUser(Long userId) {
        return productRepository.findAll().stream()
                .filter(product -> product.getSeller().getId().equals(userId))
                .toList();
    }

    // Get all products bought by a user
    public List<Product> getProductsBoughtByUser(Long userId) {
        return productRepository.findAll().stream()
                .filter(product -> product.getBuyer() != null && product.getBuyer().getId().equals(userId))
                .toList();
    }

//    public Product addProduct(Product product, MultipartFile file) {
//
//    }
}
