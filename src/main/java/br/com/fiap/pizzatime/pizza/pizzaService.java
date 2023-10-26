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

    public boolean delete(Long id){

        var pizza = repository.findById(id);

        if(pizza.isEmpty()) return false;
        
        repository.deleteById(id);
        
        return true;

    }
    
    public void save(Pizza pizza) {
        repository.save(pizza);
    }
}
