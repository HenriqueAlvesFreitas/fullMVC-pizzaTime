package br.com.fiap.pizzatime.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class pizzaService {

    @Autowired
    pizzaRepository repository;

    public List<Pizza> FindAll(){

        return repository.findAll();
    }
    
}
