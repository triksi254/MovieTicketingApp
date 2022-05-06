package com.example.ticketing.security_configuration;

import com.example.ticketing.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//This is re-factored code for the users' security configuration
@EnableWebSecurity


public class AdminSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminDetailsServiceImpl adminDetailsServiceImpl;

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
//    }
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/register",
                        "register/verify",
                        "/login",
                        "/dashboard",
                        "/movies/showForm",
                        "/movieIndex",
                        "/spectacles/showForm",
                        "/movies/edit/*",
                        "/spectacles/edit/*",
                        "/movies/update/*",
                        "/spectacles/update/*",
                        "/movies/delete/*",
                        "/spectacles/delete/*",
                        "/movies/add",
                        "/spectacles/add",
                        "/movies/user/newRepertoire",
                        "/movies/user/updateRepertoire",
                        "/movies/reservation",
                        "/movies/reservation/*",
                        "/spectacles/reservation",
                        "/spectacles/reservation",
//                        "/sign-up",
//                        "/js/**",
                        "/css/**",
                        "/images/**",
//                        "/vendor/**",
                        "/webjars/**"
                )
                .permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/dashboard")
                .and()
                .logout().deleteCookies("remove")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(logoutSuccessHandler())
//                .logoutUrl("/logout")
                .permitAll()
                .and()
                .sessionManagement().invalidSessionUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        http.csrf().disable();
    }

}
