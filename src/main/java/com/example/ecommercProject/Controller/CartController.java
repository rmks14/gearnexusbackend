//package com.example.ecommercProject.Controller;
//
//import com.example.ecommercProject.Repository.CartRepository;
//import com.example.ecommercProject.Repository.ProductRepository;
//import com.example.ecommercProject.Repository.UserRepository;
//import com.example.ecommercProject.model.Cart;
//import com.example.ecommercProject.model.Product;
//import com.example.ecommercProject.model.User;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/cart")
//public class CartController {
//    private final CartRepository cartRepository;
//    private final ProductRepository productRepository;
//    private final UserRepository userRepository;
//
//    public CartController(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
//        this.cartRepository = cartRepository;
//        this.productRepository = productRepository;
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<?> addToCart(@RequestParam Long userId, @RequestParam Long productId) {
//        Optional<User> user = userRepository.findById(userId);
//        Optional<Product> product = productRepository.findById(productId);
//        if (user.isPresent() && product.isPresent()) {
//            Cart cart = new Cart();
//            cartRepository.save(cart);
//            return ResponseEntity.ok("Product added to cart");
//        }
//        return ResponseEntity.badRequest().body("Invalid user or product");
//    }
//}
//


package com.example.ecommercProject.Controller;

import com.example.ecommercProject.Repository.CartRepository;
import com.example.ecommercProject.Repository.ProductRepository;
import com.example.ecommercProject.Repository.UserRepository;
import com.example.ecommercProject.Service.CartService;
import com.example.ecommercProject.model.Cart;
import com.example.ecommercProject.model.Product;
import com.example.ecommercProject.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/cart")
//public class CartController {
//
//    private final CartRepository cartRepository;
//    private final ProductRepository productRepository;
//    private final UserRepository userRepository;
//
//    public CartController(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
//        this.cartRepository = cartRepository;
//        this.productRepository = productRepository;
//        this.userRepository = userRepository;
//    }
//
//    // ✅ Add product to cart (increase quantity if already exists)
//    @PostMapping("/add")
//    public ResponseEntity<?> addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        Optional<Product> productOptional = productRepository.findById(productId);
//
//        if (userOptional.isPresent() && productOptional.isPresent()) {
//            User user = userOptional.get();
//            Product product = productOptional.get();
//
//            Optional<Cart> existingCartItem = cartRepository.findByUserAndProduct(user, product);
//
//            if (existingCartItem.isPresent()) {
//                // If product already in cart, increase quantity
//                Cart cart = existingCartItem.get();
//                cart.setQuantity(cart.getQuantity() + quantity);
//                cartRepository.save(cart);
//                return ResponseEntity.ok("Product quantity updated in cart");
//            } else {
//                // If product not in cart, add new entry
//                Cart cart = new Cart(user, product, quantity);
//                cartRepository.save(cart);
//                return ResponseEntity.ok("Product added to cart");
//            }
//        }
//        return ResponseEntity.badRequest().body("Invalid user or product");
//    }
//
//    // ✅ Get all cart items for a user
//    @GetMapping("/{userId}")
//    public ResponseEntity<?> getUserCart(@PathVariable Long userId) {
//        return ResponseEntity.ok(cartRepository.findByUserId(userId));
//    }
//
//    // ✅ Remove a single item from cart
//    @DeleteMapping("/remove/{cartId}")
//    public ResponseEntity<?> removeFromCart(@PathVariable Long cartId) {
//        if (cartRepository.existsById(cartId)) {
//            cartRepository.deleteById(cartId);
//            return ResponseEntity.ok("Item removed from cart");
//        }
//        return ResponseEntity.badRequest().body("Cart item not found");
//    }
//
//    // ✅ Clear entire cart for a user
//    @DeleteMapping("/clear/{userId}")
//    public ResponseEntity<?> clearCart(@PathVariable Long userId) {
//        cartRepository.deleteByUserId(userId);
//        return ResponseEntity.ok("Cart cleared");
//    }
//}


@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow frontend requests
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartController(CartService cartService, UserRepository userRepository, ProductRepository productRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // ✅ Add product to cart
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam(defaultValue = "1") int quantity) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (userOptional.isEmpty() || productOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid user or product"));
        }

        Cart cartItem = cartService.addToCart(userOptional.get(), productOptional.get(), quantity);
        return ResponseEntity.ok(Map.of("message", "Product added to cart", "cartId", cartItem.getId()));
    }

    // ✅ Get cart items
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

    // ✅ Remove item from cart
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long cartId) {
        if (!cartService.removeFromCart(cartId)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Cart item not found"));
        }
        return ResponseEntity.ok(Map.of("message", "Item removed from cart"));
    }

    // ✅ Clear cart
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<?> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok(Map.of("message", "Cart cleared"));
    }
}


