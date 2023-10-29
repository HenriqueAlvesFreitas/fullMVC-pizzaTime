package br.com.fiap.pizzatime.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    
    @Autowired
    userRepository repository;

    public void addUser(User user){
        var userDb = repository.findById(user.getId()).get();
        repository.save(userDb);
    }

}
