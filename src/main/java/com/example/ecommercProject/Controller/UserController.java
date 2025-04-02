
package com.example.ecommercProject.Controller;
//
//import com.example.ecommercProject.Repository.ProductRepository;
//import com.example.ecommercProject.Repository.UserRepository;
//import com.example.ecommercProject.Service.JwtService;
//import com.example.ecommercProject.Service.Userservice;
//import com.example.ecommercProject.model.Product;
//import com.example.ecommercProject.model.User;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//    private final UserRepository userRepository;
//    private final Userservice userservice;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//    private final ProductRepository productRepository;
//
//    public UserController(UserRepository userRepository,
//                          Userservice userservice,
//                          JwtService jwtService,
//                          AuthenticationManager authenticationManager,
//                          ProductRepository productRepository) {
//        this.userRepository = userRepository;
//        this.userservice = userservice;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//        this.productRepository = productRepository;
//    }
//
//    // ✅ Register a new user
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        User savedUser = userservice.saveUser(user);
//        return ResponseEntity.ok(savedUser);
//    }
//
//    // ✅ User login (JWT Authentication)
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//            );
//
//            if (authentication.isAuthenticated()) {
//                String token = jwtService.generateToken(user.getUsername());
//                return ResponseEntity.ok(token);
//            } else {
//                return ResponseEntity.status(401).body("Invalid credentials");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body("Authentication failed");
//        }
//    }
//
//    // ✅ Get user profile
//    @GetMapping("/profile/{userId}")
//    public ResponseEntity<Object> getUserProfile(@PathVariable Long userId) {
//        Optional<User> user = userRepository.findById(userId);
//        return user.<ResponseEntity<Object>>map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.badRequest().body("User not found"));
//    }
//
//    // ✅ Update user profile
//    @PutMapping("/update/{userId}")
//    public ResponseEntity<?> updateUserProfile(@PathVariable Long userId, @RequestBody User updatedUser) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setAddress(updatedUser.getAddress());
//            user.setMobileNumber(updatedUser.getMobileNumber());
//            userRepository.save(user);
//            return ResponseEntity.ok("Profile updated successfully");
//        }
//        return ResponseEntity.badRequest().body("User not found");
//    }
//
//    // ✅ Get products sold by the user
//    @GetMapping("/{userId}/sold-products")
//    public ResponseEntity<List<Product>> getSoldProducts(@PathVariable Long userId) {
//        List<Product> soldProducts = productRepository.findBySellerId(userId);
//        return ResponseEntity.ok(soldProducts);
//    }
//
//    // ✅ Get products bought by the user
//    @GetMapping("/{userId}/bought-products")
//    public ResponseEntity<List<Product>> getBoughtProducts(@PathVariable Long userId) {
//        List<Product> boughtProducts = productRepository.findByBuyerId(userId);
//        return ResponseEntity.ok(boughtProducts);
//    }
//}



//
//
//import com.example.ecommercProject.Service.JwtService;
//import com.example.ecommercProject.Service.Userservice;
//import com.example.ecommercProject.model.User;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow frontend requests
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//    private final Userservice userservice;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public UserController(Userservice userservice, JwtService jwtService, AuthenticationManager authenticationManager) {
//        this.userservice = userservice;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    // ✅ Register User
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        User savedUser = userservice.saveUser(user);
//        return ResponseEntity.ok(Map.of(
//                "message", "User registered successfully",
//                "userId", savedUser.getId(),
//                "username", savedUser.getUsername()
//        ));
//    }
//
//    // ✅ Login User (JWT Authentication)
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//            );
//
//            if (authentication.isAuthenticated()) {
//                String token = jwtService.generateToken(user.getUsername());
//                return ResponseEntity.ok(Map.of(
//                        "message", "Login successful",
//                        "token", token
//                ));
//            } else {
//                return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body(Map.of("error", "Authentication failed"));
//        }
//    }
//}


import com.example.ecommercProject.Service.JwtService;
import com.example.ecommercProject.Service.Userservice;
import com.example.ecommercProject.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Userservice userservice;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;

    public UserController(Userservice userservice, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userservice = userservice;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.encoder = new BCryptPasswordEncoder();
    }

    // ✅ Register User
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword())); // Encrypt password
        User savedUser = userservice.saveUser(user);
        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "userId", savedUser.getId(),
                "username", savedUser.getUsername()
        ));
    }

    // ✅ Login User (JWT Authentication)
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            User existingUser = userservice.getUserByUsername(user.getUsername());
//
//            if (existingUser == null || !encoder.matches(user.getPassword(), existingUser.getPassword())) {
//                return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
//            }
//
//            String token = jwtService.generateToken(existingUser.getUsername());
//            return ResponseEntity.ok(Map.of(
//                    "message", "Login successful",
//                    "token", token,
//                    "userId", existingUser.getId()
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body(Map.of("error", "Authentication failed"));
//        }
//    }
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            User existingUser = userservice.getUserByUsername(user.getUsername());
//
//            if (existingUser == null || !encoder.matches(user.getPassword(), existingUser.getPassword())) {
//                return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
//            }
//
//
//            String token = jwtService.generateToken(existingUser.getUsername());
//            return ResponseEntity.ok(Map.of(
//                    "message", "Login successful",
//                    "token", token,
//                    "userId", existingUser.getId()
//            ));
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body(Map.of("error", "Authentication failed"));
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User existingUser = userservice.getUserByUsername(user.getUsername());

            if (existingUser == null) {
                System.out.println("User not found!");
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            if (!encoder.matches(user.getPassword(), existingUser.getPassword())) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            // Generate JWT token after successful authentication
            String token = jwtService.generateToken(existingUser.getUsername());
            return ResponseEntity.ok(Map.of(
                    "message", "Login successful",
                    "token", token,
                    "userId", existingUser.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Authentication failed"));
        }
    }




    // ✅ Get User Profile (Includes Products Sold & Bought)
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String token) {
        String username = jwtService.extractUserName(token.replace("Bearer ", ""));
        User user = userservice.getUserByUsername(username);

        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        Map<String, Object> profileData = new HashMap<>();
        profileData.put("userId", user.getId());
        profileData.put("username", user.getUsername());
        profileData.put("email", user.getEmail());
        profileData.put("mobileNumber", user.getMobileNumber());
        profileData.put("address", user.getAddress());
        profileData.put("productsSold", user.getSellingProducts());
        profileData.put("productsBought", user.getPurchasedProducts());


        return ResponseEntity.ok(profileData);
    }



    // ✅ Update Address & Mobile Number
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser, @RequestHeader("Authorization") String token) {
        String username = jwtService.extractUserName(token.replace("Bearer ", ""));
        User existingUser = userservice.getUserByUsername(username);

        if (existingUser == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        // Update fields
        if (updatedUser.getAddress() != null) {
            existingUser.setAddress(updatedUser.getAddress());
        }
        if (updatedUser.getMobileNumber() != null) {
            existingUser.setMobileNumber(updatedUser.getMobileNumber());
        }

        userservice.saveUser(existingUser);
        return ResponseEntity.ok(Map.of("message", "Profile updated successfully"));
    }

    // ✅ Change Password
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> passwordData,
                                            @RequestHeader("Authorization") String token) {
        String username = jwtService.extractUserName(token.replace("Bearer ", ""));
        User user = userservice.getUserByUsername(username);

        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        // Validate old password
        if (!encoder.matches(passwordData.get("oldPassword"), user.getPassword())) {
            return ResponseEntity.status(400).body(Map.of("error", "Old password is incorrect"));
        }

        // Set new password
        user.setPassword(encoder.encode(passwordData.get("newPassword")));
        userservice.saveUser(user);
        return ResponseEntity.ok(Map.of("message", "Password updated successfully"));
    }
}
