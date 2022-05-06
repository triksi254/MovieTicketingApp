package com.example.ticketing.controller;

import com.example.ticketing.model.SecureToken;
import com.example.ticketing.model.UserData;
import com.example.ticketing.service.UserService;
import com.example.ticketing.service.UserServiceImpl;
import com.example.ticketing.service.UserServiceImpl.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;


@Controller
public class UserController {

    private static final String REDIRECT_LOGIN= "redirect:/login";
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private MessageSource messageSource;
    @GetMapping("/")
    public ModelAndView DefaultHome () {
        System.out.println("***********************Entered login**********************");
        return new ModelAndView("login");

    }
    @RequestMapping("/register")
    @GetMapping("/register")
    public String register(final Model model) {
        System.out.println("GET USER Details");

        model.addAttribute("userData", new UserData());
        return "/register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid UserData userData, final BindingResult bindingResult, final Model model) throws InvalidTokenException, UserService.UserAlreadyExistsException {


        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", userData);
            return "register";
        }

        try {
            userServiceImpl.register(userData);
        } catch (UserService.UserAlreadyExistsException e) {
            bindingResult.rejectValue("email", "userData.email", "An account already exists for this email.");
            model.addAttribute("registrationForm", userData);
            return "register";
        }
        return "/register";
    }
    @GetMapping("/verify")
    public String verifyCustomer(@RequestParam(required = false) SecureToken token, final Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(String.valueOf(token))) {
            redirectAttributes.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.missing.token", null, LocaleContextHolder.getLocale()));
            return REDIRECT_LOGIN;
        }
        try {
            userServiceImpl.verifyUser(String.valueOf((token)));
        } catch (InvalidTokenException e) {
            redirectAttributes.addFlashAttribute("tokenError", messageSource.getMessage("user.registration.verification.invalid.token", null,LocaleContextHolder.getLocale()));
            return REDIRECT_LOGIN;
        }

        redirectAttributes.addFlashAttribute("verifiedAccountMsg", messageSource.getMessage("user.registration.verification.success", null,LocaleContextHolder.getLocale()));
        return REDIRECT_LOGIN;
    }

        @RequestMapping("/login")
        public ModelAndView login () {
            return new ModelAndView("login");
        }

        @RequestMapping("/dashboard")
        public ModelAndView dashboard () {
            return new ModelAndView("dashboard");
        }


        /***modified dashboard model and view to include the user/ admin role difference during login***/
//    public String dashboard(Model model) {
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Collection<GrantedAuthority> authorities = principal.getAuthorities();
//
//        Object[] objects = authorities.toArray();
//
//        Object role = objects[0];
//        System.out.println(role);
//
//        model.addAttribute("username", principal.getUsername());
//        model.addAttribute("role", role);
//        return "dashboard";
//    }
//

}

