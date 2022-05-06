package com.example.ticketing.service;

import com.example.ticketing.email_token_generation.AccountVerificationEmailContext;

import javax.mail.MessagingException;

public interface EmailService {
    String sendMail(AccountVerificationEmailContext email) throws MessagingException;
}
