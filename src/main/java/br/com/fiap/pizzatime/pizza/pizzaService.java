package br.com.fiap.pizzatime.pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import br.com.fiap.pizzatime.user.User;

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

    public void decrement(Long id) {
        //buscar a tarefa no bd
        var optional = repository.findById(id);

        if (optional.isEmpty()) throw new RuntimeException("Pizza não encontrada");

        var pizza = optional.get();

        if (pizza.getStatus() == null || pizza.getStatus() <= 0) 
            throw new RuntimeException("Pizza não pode ter status negativo");

        pizza.setStatus(pizza.getStatus() - 10);


        //salvar
        repository.save(pizza);
    }

    public void increment(Long id) {
        //buscar a tarefa no bd
        var optional = repository.findById(id);

        if (optional.isEmpty()) throw new RuntimeException("Pizza não encontrada");

        var pizza = optional.get();

        if (pizza.getStatus() == 100){
            throw new RuntimeException("tarefa não pode ter status maior que 100");
        }

        pizza.setStatus(pizza.getStatus() + 10);


        //salvar
        repository.save(pizza);
    }

    public void recivePizza(Long id, OAuth2User user) {

        var optional = repository.findById(id);

        if(optional.isEmpty())
            throw new RuntimeException("Pizza não encontrada");

        var pizza = optional.get();

        if(pizza.getUser() != null)
            throw new RuntimeException("Pizza já atribuida");

        pizza.setUser(User.convert(user));

        repository.save(pizza);
        
    }

    public void dropPizza(Long id, OAuth2User user){

        var optional = repository.findById(id);

        if(optional.isEmpty())
            throw new RuntimeException("Pizza não encontrada");
        
        var pizza = optional.get();

        if(pizza.getUser() == null)
            throw new RuntimeException("Pizza não atribuida");

        if(!pizza.getUser().equals(User.convert(user)))
            throw new RuntimeException("Não pode largar pizza de outra pessoa");

        pizza.setUser(null);

        repository.save(pizza);
    }

}
