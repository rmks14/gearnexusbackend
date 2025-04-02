package com.example.ecommercProject.Controller;

import com.example.ecommercProject.Service.ProductService;

import com.example.ecommercProject.Service.Userservice;
import com.example.ecommercProject.model.Product;
import com.example.ecommercProject.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.security.Principal;

//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//
//    @Autowired
//    private  ProductService productService;
//    @Autowired
//    private Userservice userService;
//
//    public ProductController(ProductService productService,Userservice userService) {
//        this.productService = productService;
//        this.userService = userService;
//    }
//
//    // Get all products
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//    // Get product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        return productService.getProductById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Add new product (seller is recorded)
//    @PostMapping("/add/{userId}")
//    public ResponseEntity<Product> addProduct(@PathVariable Long userId, @RequestBody Product product) {
//        Optional<User> userOptional = userService.getUserById(userId);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            product.setSeller(user);  // Save the user as the seller
//            Product savedProduct = productService.addProduct(product);
//            return ResponseEntity.ok(savedProduct);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    // Update product details
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
//        Optional<Product> productOptional = productService.getProductById(id);
//
//        if (productOptional.isPresent()) {
//            Product product = productOptional.get();
//            product.setName(updatedProduct.getName());
//            product.setDescription(updatedProduct.getDescription());
//            product.setPrice(updatedProduct.getPrice());
//            product.setImageUrl(updatedProduct.getImageUrl());
//            product.setCategory(updatedProduct.getCategory());
//
//            Product savedProduct = productService.addProduct(product);
//            return ResponseEntity.ok(savedProduct);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    // Delete a product
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        Optional<Product> productOptional = productService.getProductById(id);
//
//        if (productOptional.isPresent()) {
//            productService.deleteProduct(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    // Buy a product (assign buyer)
//    @PostMapping("/buy/{productId}/user/{userId}")
//    public ResponseEntity<Product> buyProduct(@PathVariable Long productId, @PathVariable Long userId) {
//        Optional<Product> productOptional = productService.getProductById(productId);
//        Optional<User> userOptional = userService.getUserById(userId);
//
//        if (productOptional.isPresent() && userOptional.isPresent()) {
//            Product product = productOptional.get();
//            User user = userOptional.get();
//
//            if (product.getBuyer() != null) {
//                return ResponseEntity.badRequest().body(null);  // Product already bought
//            }
//
//            product.setBuyer(user);  // Assign the buyer
//            Product updatedProduct = productService.addProduct(product);
//            return ResponseEntity.ok(updatedProduct);
//        }
//        return ResponseEntity.notFound().build();
//    }
//}




//
//@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow frontend requests
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//
//    private final ProductService productService;
//    private final Userservice userService;
//
//    public ProductController(ProductService productService, Userservice userService) {
//        this.productService = productService;
//        this.userService = userService;
//    }
//
//    // ✅ Get all products
//    @GetMapping
//    public ResponseEntity<List<Product>> getAllProducts() {
//        return ResponseEntity.ok(productService.getAllProducts());
//    }
//
//    // ✅ Get product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getProductById(@PathVariable Long id) {
//        return productService.getProductById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.status(404).body((Product) Map.of("error", "Product not found")));
//    }
//
//    // ✅ Add product (only authenticated users)
//    @PostMapping("/add/{userId}")
//    public ResponseEntity<?> addProduct(@PathVariable Long userId, @RequestBody Product product) {
//        Optional<User> userOptional = userService.getUserById(userId);
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
//        }
//
//        product.setSeller(userOptional.get());
//        Product savedProduct = productService.addProduct(product);
//        return ResponseEntity.ok(Map.of("message", "Product added successfully", "productId", savedProduct.getId()));
//    }
//
//    // ✅ Delete product (only if it exists)
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
//        if (!productService.getProductById(id).isPresent()) {
//            return ResponseEntity.status(404).body(Map.of("error", "Product not found"));
//        }
//
//        productService.deleteProduct(id);
//        return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
//    }
//}


//
//@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow frontend requests
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//
//    private final ProductService productService;
//    private final Userservice userService;
//
//    public ProductController(ProductService productService, Userservice userService) {
//        this.productService = productService;
//        this.userService = userService;
//    }
//
//    // ✅ Get all available products (Only unsold products in marketplace)
//    @GetMapping
//    public ResponseEntity<List<Product>> getAllAvailableProducts() {
//        return ResponseEntity.ok(productService.getAvailableProducts());
//    }
//
//    // ✅ Get product by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getProductById(@PathVariable Long id) {
//        return productService.getProductById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.status(404).body(Map.of("error", "Product not found")));
//    }
//
//    // ✅ Add product (User can list products for sale)
//    @PostMapping("/add/{userId}")
//    public ResponseEntity<?> addProduct(@PathVariable Long userId, @RequestBody Product product) {
//        Optional<User> userOptional = userService.getUserById(userId);
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
//        }
//
//        product.setSeller(userOptional.get());
//        product.setBuyer(null); // Mark product as unsold
//        Product savedProduct = productService.addProduct(product);
//        return ResponseEntity.ok(Map.of("message", "Product added successfully", "productId", savedProduct.getId()));
//    }
//
//    // ✅ Purchase product (Removes product from marketplace)
//    @PostMapping("/purchase/{userId}/{productId}")
//    public ResponseEntity<?> purchaseProduct(@PathVariable Long userId, @PathVariable Long productId) {
//        Optional<User> userOptional = userService.getUserById(userId);
//        Optional<Product> productOptional = productService.getProductById(productId);
//
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(404).body(Map.of("error", "Buyer not found"));
//        }
//        if (productOptional.isEmpty()) {
//            return ResponseEntity.status(404).body(Map.of("error", "Product not found"));
//        }
//
//        Product product = productOptional.get();
//        if (product.getBuyer() != null) {
//            return ResponseEntity.status(400).body(Map.of("error", "Product already sold"));
//        }
//
//        // ✅ Assign buyer and mark product as sold
//        User buyer = userOptional.get();
//        product.setBuyer(buyer);
//        productService.updateProduct(product);
//
//        return ResponseEntity.ok(Map.of("message", "Product purchased successfully"));
//    }
//
//    // ✅ Delete product (Only if it exists)
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
//        if (!productService.getProductById(id).isPresent()) {
//            return ResponseEntity.status(404).body(Map.of("error", "Product not found"));
//        }
//
//        productService.deleteProduct(id);
//        return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
//    }
//
//    // ✅ Update product details (Seller can update their product)
//    @PutMapping("/update/{productId}")
//    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
//        Optional<Product> productOptional = productService.getProductById(productId);
//
//        if (productOptional.isEmpty()) {
//            return ResponseEntity.status(404).body(Map.of("error", "Product not found"));
//        }
//
//        Product existingProduct = productOptional.get();
//        existingProduct.setName(updatedProduct.getName());
//        existingProduct.setPrice(updatedProduct.getPrice());
//
//        productService.updateProduct(existingProduct);
//        return ResponseEntity.ok(Map.of("message", "Product updated successfully"));
//    }
//}




import com.example.ecommercProject.model.Product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow frontend requests
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    private Userservice userservice;
    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    // ✅ Get all available products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // ✅ Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 without type mismatch
    }



//    @PostMapping(value = "/add/{userId}", consumes = "multipart/form-data")
//    public ResponseEntity<?> addProduct(@PathVariable Long userId,
//                                        @RequestParam("name") String name,
//                                        @RequestParam("description") String description,
//                                        @RequestParam("price") double price,
//                                        @RequestParam("category") String category,
//                                        @RequestParam("image") MultipartFile file,
//                                        Principal principal) {
//        System.out.println("Authenticated User: " + principal.getName());
//
//        Optional<User> userOptional = userservice.getUserById(userId);
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
//        }
//
//        // Ensure the authenticated user matches the provided userId
//        if (!principal.getName().equals(userOptional.get().getUsername())) {
//            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized to add product"));
//        }
//
//        try {
//            Product product = new Product();
//            product.setName(name);
//            product.setDescription(description);
//            product.setPrice(price);
//            product.setCategory(category);
//            product.setSeller(userOptional.get()); // Assign seller before saving
//            product.setImageData(file.getBytes()); // Convert image to byte array
//
//            Product savedProduct = productService.addProduct(product);
//            return ResponseEntity.ok(Map.of("message", "Product added successfully", "productId", savedProduct.getId()));
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(Map.of("error", "File upload failed"));
//        }
//    }

//    @PostMapping(value = "/add/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> addProduct(@PathVariable Long userId,
//                                        @RequestPart("name") String name,
//                                        @RequestPart("description") String description,
//                                        @RequestPart("price") double price,
//                                        @RequestPart("category") String category,
//                                        @RequestPart("location") String location,
//                                        @RequestPart("image") MultipartFile file,
//                                        Principal principal) {
//
//        System.out.println("adding");
////        if (principal == null) {
////            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized: No authentication detected"));
////        }
//        System.out.println("Authenticated User: " + principal.getName());
//
//        Optional<User> userOptional = userservice.getUserById(userId);
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
//        }
//
//        // Ensure authenticated user matches the provided userId
//        if (!principal.getName().equals(userOptional.get().getUsername())) {
//            return ResponseEntity.status(403).body(Map.of("error", "Unauthorized to add product"));
//        }
//
//        try {
//            Product product = new Product();
//            product.setName(name);
//            product.setDescription(description);
//            product.setPrice(price);
//            product.setCategory(category);
//            product.setLocation(location); // ✅ Set location
//            product.setSeller(userOptional.get()); // ✅ Assign seller before saving
//            product.setImageData(file.getBytes()); // ✅ Store image as byte array
//
//            Product savedProduct = productService.addProduct(product);
//            return ResponseEntity.ok(Map.of("message", "Product added successfully", "productId", savedProduct.getId()));
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(Map.of("error", "File upload failed"));
//        }
//    }



//@PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//public ResponseEntity<?> addProduct(
//        @RequestParam("name") String name,
//        @RequestParam("description") String description,
//        @RequestParam("price") double price,
//        @RequestParam("category") String category,
//        @RequestParam("location") String location,
//        @RequestParam("image") MultipartFile image,
//        Authentication authentication) { // Ensures user is logged in
//
//    String username = authentication.getName(); // Get current logged-in user
////    Optional<User> userOpt = UserRepository.findByUsername(username);
////
////    if (!userOpt.isPresent()) {
////        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
////    }
//
//    try {
//        Product product = new Product();
//        product.setName(name);
//        product.setDescription(description);
//        product.setPrice(price);
//        product.setCategory(category);
//        product.setLocation(location);
//        product.setImageData(image.getBytes());
////        product.setSeller(userOpt.get()); // Save logged-in user as seller
//
//        Product savedProduct = productService.addProduct(product,image);
//        return ResponseEntity.ok(savedProduct);
//    } catch (IOException e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image");
//    }
//}



//@PostMapping("/add/{userId}")
//public ResponseEntity<?> addProduct(@PathVariable Long userId, @RequestBody Product product, Principal principal, HttpServletRequest request) {
//    String authHeader = request.getHeader("Authorization");
//    System.out.println("Authorization Header: " + authHeader);
//    System.out.println("Authenticated User: " + (principal != null ? principal.getName() : "NULL"));
//
//    if (principal == null) {
//        return ResponseEntity.status(403).body(Map.of("error", "User is not authenticated"));
//    }
//
//    Optional<User> userOptional = userservice.getUserById(userId);
//    if (userOptional.isEmpty()) {
//        return ResponseEntity.status(404).body(Map.of("error", "User not found"));
//    }
//
//    // Ensure the authenticated user matches the provided userId
//    if (!principal.getName().equals(userOptional.get().getUsername())) {
//        return ResponseEntity.status(403).body(Map.of("error", "Unauthorized to add product"));
//    }
//
//    product.setSeller(userOptional.get());
//    Product savedProduct = productService.addProduct(product);
//    return ResponseEntity.ok(Map.of("message", "Product added successfully", "productId", savedProduct.getId()));
//}

//    @PostMapping("/add/{userId}")
//    public ResponseEntity<?>addProduct(@PathVariable Long userId,@RequestPart("product") Product product,
//                                       @RequestPart("file") MultipartFile file){
//        try {
//            if (product == null || file == null) {
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            }
//
//            Product savedProduct = productService.addProduct(userId, product, file);
//            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//@PostMapping(value = "/add/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//public ResponseEntity<?> addProduct(
//        @PathVariable Long userId,
//        @RequestPart("product") String productJson,
//        @RequestPart("file") MultipartFile file) {
//    try {
//        if (productJson == null || file == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        // Convert JSON string to Product object
//        ObjectMapper objectMapper = new ObjectMapper();
//        Product product = objectMapper.readValue(productJson, Product.class);
//
//        Product savedProduct = productService.addProduct(userId, product, file);
//        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
//    } catch (Exception e) {
//        e.printStackTrace();
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}



@PostMapping("/add/{userId}")
public ResponseEntity<?> addProduct(
        @PathVariable Long userId,
        @RequestPart("product") String productJson,
        @RequestPart("file") MultipartFile file) {
    try {
        // ✅ Convert JSON string to Product object
        Product product = objectMapper.readValue(productJson, Product.class);

        // ✅ Check if User exists
        Optional<User> userOptional = userservice.getUserById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        product.setSeller(userOptional.get());

        // ✅ Convert file to byte[]
        if (!file.isEmpty()) {
            product.setImageData(file.getBytes());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is missing");
        }

        // ✅ Save product
        Product savedProduct = productService.addProduct(userId, product, file);

        return ResponseEntity.ok().body("Product added successfully! ID: " + savedProduct.getId());

    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}


    @GetMapping(value = "/image/{productId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long productId) {
        Optional<Product> productOptional = productService.getProductById(productId);

        if (productOptional.isPresent() && productOptional.get().getImageData() != null) {
            byte[] imageData = productOptional.get().getImageData();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
        }
        return ResponseEntity.notFound().build();
    }



    // ✅ Delete product (only if it exists)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Product not found");
        }
    }

    // ✅ Buy a product
    @PostMapping("/buy/{productId}/buyer/{buyerId}")
    public ResponseEntity<?> buyProduct(@PathVariable Long productId, @PathVariable Long buyerId) {
        Optional<Product> purchasedProduct = productService.purchaseProduct(productId, buyerId);

        if (purchasedProduct.isPresent()) {
            return ResponseEntity.ok("Product purchased successfully");
        } else {
            return ResponseEntity.status(400).body("Purchase failed: Product may already be sold");
        }
    }

    // ✅ Get all products sold by a user
    @GetMapping("/sold/{userId}")
    public ResponseEntity<List<Product>> getProductsSoldByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(productService.getProductsSoldByUser(userId));
    }

    // ✅ Get all products bought by a user
    @GetMapping("/bought/{userId}")
    public ResponseEntity<List<Product>> getProductsBoughtByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(productService.getProductsBoughtByUser(userId));
    }

//    @PutMapping("/update/{productId}")
//    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
//        Optional<Product> productOptional = productService.getProductById(productId);
//
//        if (productOptional.isEmpty()) {
//            return ResponseEntity.status(404).body(Map.of("error", "Product not found"));
//        }
//
//        Product existingProduct = productOptional.get();
//        existingProduct.setName(updatedProduct.getName());
//        existingProduct.setPrice(updatedProduct.getPrice());
//
//        productService.addProduct(existingProduct,f); // ✅ Save the updated product
//        return ResponseEntity.ok(Map.of("message", "Product updated successfully"));
//    }

}
