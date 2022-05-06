package com.example.ticketing.service;

import com.example.ticketing.model.SecureToken;
import com.example.ticketing.model.UserData;
import com.example.ticketing.model.Users;

public interface UserService {

    boolean checkIfUserExist(String email);

    void register(UserData userData) throws UserServiceImpl.UserAlreadyExistsException, UserServiceImpl.InvalidTokenException;

    void sendRegistrationConfirmationEmail(Users users) throws UserServiceImpl.InvalidTokenException;


    boolean verifyUser(String token) throws UserServiceImpl.InvalidTokenException;

    void verifyCustomer(String token);


    class UserAlreadyExistsException extends Exception {
        public UserAlreadyExistsException(Object o) {

        }
    }

   class InvalidTokenException extends Exception{
       public InvalidTokenException(SecureToken s){

       }
    }
}
