package br.com.fiap.pizzatime.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long>{
    
}
