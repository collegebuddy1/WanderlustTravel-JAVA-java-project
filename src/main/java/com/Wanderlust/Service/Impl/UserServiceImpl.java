package com.Wanderlust.Service.Impl;

import com.Wanderlust.Exception.UserException;
import com.Wanderlust.Model.User;
import com.Wanderlust.Model.Wallet;
import com.Wanderlust.Repository.UserRepository;
import com.Wanderlust.Repository.WalletRepository;
import com.Wanderlust.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    /**
     * Registers a new user.
     *
     * @param user The user object to be registered.
     * @return The registered user details.
     */
    @Override
    @Transactional
    public User registerCustomer(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserException("User already exists");
        }

        Wallet wallet = new Wallet();
        wallet.setBalance(0.0);
        wallet.setUser(user);
        user.setWallet(wallet);
        return userRepository.save(user);
    }

    /**
     * Retrieves user details by email.
     *
     * @param email The email of the user to retrieve.
     * @return The user details.
     * @throws UserException If the user is not found.
     */
    @Override
    public User getUserDetailsByEmail(String email) throws UserException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
    }

    /**
     * Retrieves details of all users.
     *
     * @return A list of all user details.
     * @throws UserException If no users are found.
     */
    @Override
    public List<User> getAllUsersDetails() throws UserException {
        if (userRepository.findAll().isEmpty()) {
            throw new UserException("No users found");
        }
        return userRepository.findAll();
    }

    /**
     * Updates user details by email.
     *
     * @param email The email of the user to update.
     * @param users The updated user details.
     * @return The updated user details.
     * @throws UserException If the user is not found.
     */
    @Override
    @Transactional
    public User updateUserDetailsByEmail(String email, User users) throws UserException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        user.setName(users.getName());
        user.setPassword(users.getPassword());
        user.setAddress(users.getAddress());
        return userRepository.save(user);
    }

    /**
     * Deletes a user by email.
     *
     * @param email The email of the user to delete.
     * @return The deleted user details.
     * @throws UserException If the user is not found.
     */
    @Override
    public User deleteUserEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        userRepository.delete(user);
        return user;
    }
}
