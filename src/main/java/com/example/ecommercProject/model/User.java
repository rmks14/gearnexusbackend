//package com.example.ecommercProject.model;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String username;
//    private String email;
//    private String password;
//    private String address;
//    private String mobileNumber;
//
//    @OneToMany(mappedBy = "seller")
//    private List<Product> sellingProducts;
//
//    @OneToMany(mappedBy = "buyer")
//    private List<Product> purchasedProducts;
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", address='" + address + '\'' +
//                ", mobileNumber='" + mobileNumber + '\'' +
//                ", sellingProducts=" + sellingProducts +
//                ", purchasedProducts=" + purchasedProducts +
//                '}';
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getMobileNumber() {
//        return mobileNumber;
//    }
//
//    public void setMobileNumber(String mobileNumber) {
//        this.mobileNumber = mobileNumber;
//    }
//
//    public List<Product> getSellingProducts() {
//        return sellingProducts;
//    }
//
//    public void setSellingProducts(List<Product> sellingProducts) {
//        this.sellingProducts = sellingProducts;
//    }
//
//    public List<Product> getPurchasedProducts() {
//        return purchasedProducts;
//    }
//
//    public void setPurchasedProducts(List<Product> purchasedProducts) {
//        this.purchasedProducts = purchasedProducts;
//    }
//}
//


package com.example.ecommercProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String address;
    private String mobileNumber;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> sellingProducts;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> purchasedProducts;

    // Constructors
    public User() {}

    public User(String username, String email, String password, String address, String mobileNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<Product> getSellingProducts() {
        return sellingProducts;
    }

    public void setSellingProducts(List<Product> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    // toString() for debugging
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
