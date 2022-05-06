package com.example.ticketing.email_token_generation;



import com.example.ticketing.model.Users;
import org.springframework.web.util.UriComponentsBuilder;


public class AccountVerificationEmailContext extends AbstractEmailContext{

    private String token;

    @Override
    public <T> void init(T context){
        Users customer =(Users) context;
        put("firstName", customer.getFirstName());
        setTemplateLocation("/email-verification");
        setSubject("Complete your registration");
        setFrom("teresiawachirakabura1@gmail.com");
        setTo(customer.getEmail());
    }


    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/register/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}


