package com.example.ticketing.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class UserData extends Users implements Serializable {
    //   @NotNull(message = "Username can not be empty")
//    private String username;

    @NotBlank(message = "Firstname can not be empty")
    private String firstName;
    @NotBlank(message = "Lastname can not be empty")
    private String lastName;
    @Email
    @NotNull(message = "Email can not be empty")
    private String email;
    @NotNull(message = "Password can not be empty")
    private String password;
    private boolean accountVerified;

    public UserData() {
        this.accountVerified = false;
    }

    @Override
    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }
    //    public UserData(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    public void setPassword(String password) {
        this.password = password;
    }
//    public String getUsername() {
//        return username;
//    }
    public String getPassword() {
        return password;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
