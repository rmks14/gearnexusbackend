//package com.example.ecommercProject.Service;
//
//import com.example.ecommercProject.Repository.CartRepository;
//import com.example.ecommercProject.model.Cart;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class CartService {
//    private final CartRepository cartRepository;
//
//    public CartService(CartRepository cartRepository) {
//        this.cartRepository = cartRepository;
//    }
//
//    public List<Cart> getCartItems(Long userId) {
//        return cartRepository.findByUserId(userId);
//    }
//
//    public Cart addToCart(Cart cart) {
//        return cartRepository.save(cart);
//    }
//}

package com.example.ecommercProject.Service;

import com.example.ecommercProject.Repository.CartRepository;
import com.example.ecommercProject.model.Cart;
import com.example.ecommercProject.model.Product;
import com.example.ecommercProject.model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Get all cart items for a user
    public List<Cart> getCartItems(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Add product to cart (increase quantity if already in cart)
    public Cart addToCart(User user, Product product, int quantity) {
        Optional<Cart> existingCartItem = cartRepository.findByUserAndProduct(user, product);

        if (existingCartItem.isPresent()) {
            // If product is already in cart, increase the quantity
            Cart cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartRepository.save(cartItem);
        } else {
            // If product is not in cart, add a new entry
            Cart newCartItem = new Cart(user, product, quantity);
            return cartRepository.save(newCartItem);
        }
    }

    // Remove a single cart item
//    public void removeFromCart(Long cartId) {
//        cartRepository.deleteById(cartId);
//    }

    public boolean removeFromCart(Long cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return true;  // ✅ Indicate successful deletion
        }
        return false;  // ✅ Indicate cart item was not found
    }


    // Clear entire cart for a user
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}
