package com.example.Ticketing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DeleteMe {
    public static void main(String[] args) {
        BCryptPasswordEncoder en = new BCryptPasswordEncoder();

        String password = en.encode("password");

        System.out.println(password);
    }
}
