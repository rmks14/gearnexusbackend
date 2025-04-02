//package com.example.ecommercProject.Service;
//
//import com.example.ecommercProject.Repository.UserRepository;
//import com.example.ecommercProject.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class Userservice {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public Userservice(UserRepository userRepository){
//        this.userRepository=userRepository;
//    }
//
//    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
//
//    public User saveUser(User user){
//        user.setPassword(encoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    public Optional<User> getUserById(Long userId){
//        return userRepository.findById(userId);
//    }
//
//    public User updateUserProfile(Long userId,User updatedUser){
//        return userRepository.findById(userId)
//                .map(user -> {
//                    user.setAddress(updatedUser.getAddress());
//                    user.setMobileNumber(updatedUser.getMobileNumber());
//                    return userRepository.save(user);
//                })
//                .orElse(null);
//    }
//}

package com.example.ecommercProject.Service;

import com.example.ecommercProject.Repository.UserRepository;
import com.example.ecommercProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Userservice {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Userservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public User saveUser(User user) {
//        user.setPassword(encoder.encode(user.getPassword())); // Ensure password is encoded
//        return userRepository.save(user);
//    }

    public User saveUser(User user) {
        System.out.println("Raw Password Before Encoding: " + user.getPassword());

        // Avoid encoding if the password is already hashed
        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(encoder.encode(user.getPassword()));
        }

        System.out.println("Encoded Password Before Saving: " + user.getPassword());
        return userRepository.save(user);
    }


    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}


