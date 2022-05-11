package com.example.ticketing.security_configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//implements any custom application logic that needs to run when the user successfully logs out
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        @Autowired
        private AdminDetailsServiceImpl adminDetailsServiceImpl;

        @Override
        public void onLogoutSuccess(
                HttpServletRequest request,
                HttpServletResponse response,
                Authentication authentication)
                throws IOException, ServletException {

            super.onLogoutSuccess(request, response, authentication);
        }
    }
