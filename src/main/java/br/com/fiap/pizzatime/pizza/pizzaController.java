package br.com.fiap.pizzatime.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizza")
public class pizzaController {

    @Autowired
    pizzaService service;

    @Autowired
    MessageSource messageSource;
    
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user ){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("pizzaList", service.FindAll());
        return "pizza/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){

        if(service.delete(id)){
            redirect.addFlashAttribute("success", messageSource.getMessage("task.delete.success", null, LocaleContextHolder.getLocale()));
        }
        else{
            redirect.addFlashAttribute("error", "Pizza não foi Apagada.");
        }
        return "redirect:/pizza";
    }

    @GetMapping("new")
    public String form(Pizza pizza, Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        return "pizza/form";
    }

    @PostMapping
    public String create(@Valid Pizza pizza, BindingResult result, RedirectAttributes redirect){

        if(result.hasErrors()) return "pizza/form";

        service.save(pizza);

        redirect.addFlashAttribute("success", "Pizza cadastrada com sucesso!");

        return "redirect:/pizza";
    }

    @GetMapping("/dec/{id}")
    public String decrement(@PathVariable Long id){
        service.decrement(id);
        return "redirect:/pizza";
    }

    @GetMapping("/inc/{id}")
    public String increment(@PathVariable Long id){
        service.increment(id);
        return "redirect:/pizza";
    }

    @GetMapping("/recive/{id}")
    public String recivePizza(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){

        service.recivePizza(id, user);
        
        return "redirect:/pizza";
    }

    @GetMapping("/drop/{id}")
    public String dropPizza(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){

        service.dropPizza(id, user);

        return "redirect:/pizza";
    }
}
