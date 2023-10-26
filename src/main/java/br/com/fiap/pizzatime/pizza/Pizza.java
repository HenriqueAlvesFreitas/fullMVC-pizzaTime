package br.com.fiap.pizzatime.pizza;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Pizza {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @NotBlank
    private String flavor;
    
    @NotBlank
    private String client;

    @Size(min = 10)
    private String description;

    @NotBlank
    private String date;

    @Min(0) @Max(100)
    private Integer status;
}
