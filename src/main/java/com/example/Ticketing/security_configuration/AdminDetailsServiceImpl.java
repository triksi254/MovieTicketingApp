package com.example.ticketing.security_configuration;

import com.example.ticketing.model.Users;
import com.example.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

@Configuration
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public AdminDetailsServiceImpl() {
    }

        @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> oUser = userRepository.findByEmail(email);
        Users customer = null;
        if (oUser.isPresent()) {
            customer = oUser.get();
        }


        if (customer != null) {

            //    List<String> UsersList = new ArrayList<String>();
            if (!customer.isAccountVerified()) {
                User.withUsername(customer.getEmail());
            }
            //            for (Role role : users.getRoles()) {
//                UsersList.add(role.getRoleName());
//            }
            return User.builder()
                    .username(customer.findByEmail())
                    //change here to store encoded password in db
                    .password(customer.getPassword())
                    .disabled(customer.isDisabled())
                    .accountExpired(customer.isAccountExpired())
                    .accountLocked(customer.isAccountLocked())
                    .credentialsExpired(customer.isCredentialsExpired())
                    //   .roles(UsersList.toArray(new String[0]))
                    .authorities("USER").build();
        } else {
            throw new UsernameNotFoundException("Email is not Found");
        }
    }
}