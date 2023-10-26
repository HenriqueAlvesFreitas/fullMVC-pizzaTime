package br.com.fiap.pizzatime.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @GetMapping
    public String index(Model model){
        model.addAttribute("pizzaList", service.FindAll());
        return "pizza/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){

        if(service.delete(id)){
            redirect.addFlashAttribute("success", "Pizza Apagada com sucesso!");
        }
        else{
            redirect.addFlashAttribute("error", "Pizza não foi Apagada.");
        }
        return "redirect:/pizza";
    }

    @GetMapping("new")
    public String form(Pizza pizza){
        return "pizza/form";
    }

    @PostMapping
    public String create(@Valid Pizza pizza, BindingResult result, RedirectAttributes redirect){

        if(result.hasErrors()) return "pizza/form";

        service.save(pizza);

        redirect.addFlashAttribute("success", "Pizza cadastrada com sucesso!");

        return "redirect:/pizza";
    }
}
