package com.example.ticketing.service;


import com.example.ticketing.email_token_generation.*;
import com.example.ticketing.model.SecureToken;
import com.example.ticketing.model.UserData;
import com.example.ticketing.model.Users;
import com.example.ticketing.repository.SecureTokenRepository;
import com.example.ticketing.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.mail.MessagingException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SecureTokenServiceImpl secureTokenServiceImpl;
    @Autowired
    SecureTokenRepository secureTokenRepository;
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @Value("${site.base.url.https}")
    private String baseURL;
    private String email;
    private String token;


    @Override
    public boolean checkIfUserExist(String email) {

         Optional<Users> users = userRepository.findByEmail(email);

        return users.isPresent();

    } boolean elseif (){return userRepository.findByEmail(email).isPresent();
    }

    private void bCryptPasswordEncoder(UserData source, Users target) {
        target.setPassword(bCryptPasswordEncoder.encode(source.getPassword()));
    }

    @Override
    public void register(UserData user) throws UserAlreadyExistsException, InvalidTokenException {
        System.out.println(user.getEmail());

        if (checkIfUserExist(user.getEmail())) {
            System.out.println("User already exists for this email");
            throw new UserAlreadyExistsException("User already exists for this email");
        }
        Users users = new Users();
        BeanUtils.copyProperties(user, users);
        users.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(users);
        sendRegistrationConfirmationEmail(users);
    }
    @Override
    public void sendRegistrationConfirmationEmail(Users user) {
        SecureToken secureToken = secureTokenServiceImpl.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailServiceImpl.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {
        SecureToken secureToken = secureTokenServiceImpl.findByToken(token);
        System.out.println("I am secured Token" + secureToken.getToken());
        if (!StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()) {
            throw new InvalidTokenException("Token is not valid");
        }
        Users user = userRepository.getOne(secureToken.getUser().getId());
        user.setAccountVerified(true);
        userRepository.save(user); // let’s same user details

        // we don’t need invalid password now
        secureTokenServiceImpl.removeToken(secureToken);
        return true;
    }
    @Override
    public void verifyCustomer(String token) {
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class InvalidTokenException extends Throwable {
        public InvalidTokenException(String token_is_not_valid) {
        }
    }
}

