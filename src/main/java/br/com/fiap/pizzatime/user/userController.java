package br.com.fiap.pizzatime.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class userController {

    @Autowired
    userRepository repository;

    @GetMapping("/users")
    public String usersPage(Model model){
        model.addAttribute("users", repository.findAll());

        return "auth/users";
    }
    
    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutPage(){
        return "auth/logout";
    }
}
