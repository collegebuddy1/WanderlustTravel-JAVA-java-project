package com.Wanderlust.Controller;

import com.Wanderlust.Model.User;
import com.Wanderlust.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    /*
        {
          "name": "John Doe",
          "password": "pass123",
          "email": "johndoe@example.com",
          "address": "123 Main St, City, Country"
        }

     */

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerCustomer(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserDetailsByEmail(@PathVariable String email) {
        User user = userService.getUserDetailsByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsersDetails() {
        List<User> users = userService.getAllUsersDetails();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<User> updateUserDetailsByEmail(@PathVariable String email, @RequestBody User user) {
        User updatedUser = userService.updateUserDetailsByEmail(email, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<User> deleteUserByEmail(@PathVariable String email) {
        User deletedUser = userService.deleteUserEmail(email);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}
