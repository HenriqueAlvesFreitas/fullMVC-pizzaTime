package br.com.fiap.pizzatime.pizza;

import org.springframework.data.jpa.repository.JpaRepository;

public interface pizzaRepository extends JpaRepository<Pizza, Long> {
    
}
