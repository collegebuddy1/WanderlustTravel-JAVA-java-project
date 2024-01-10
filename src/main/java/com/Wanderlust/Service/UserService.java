package com.Wanderlust.Service;

import com.Wanderlust.Exception.UserException;
import com.Wanderlust.Model.User;

import java.util.List;

public interface UserService {

    /**
     * Registers a new customer.
     *
     * @param customer The customer object to be registered.
     * @return The registered user details.
     */
    public User registerCustomer(User customer);


    /**
     * Retrieves user details by email.
     *
     * @param email The email of the user to retrieve.
     * @return The user details.
     * @throws UserException If the user is not found.
     */
    public User getUserDetailsByEmail(String email)throws UserException;

    /**
     * Retrieves details of all users.
     *
     * @return A list of all user details.
     * @throws UserException If no users are found.
     */
    public List<User> getAllUsersDetails()throws UserException;

    /**
     * Updates user details by email.
     *
     * @param email The email of the user to update.
     * @param users The updated user details.
     * @return The updated user details.
     * @throws UserException If the user is not found.
     */
    public User updateUserDetailsByEmail(String  email, User users)throws UserException;


    /**
     * Deletes a user by email.
     *
     * @param email The email of the user to delete.
     * @return The deleted user details.
     * @throws UserException If the user is not found.
     */
    public User deleteUserEmail(String email)throws UserException;


}
