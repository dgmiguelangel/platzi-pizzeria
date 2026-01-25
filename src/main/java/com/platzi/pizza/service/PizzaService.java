package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final JdbcTemplate jdbcTemplate;

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate, PizzaRepository pizzaRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getCheapestPizzas(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getAvailable() {
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public void countVeganPizzas() {
        int veganPizzas = this.pizzaRepository.countByVeganTrue();
        System.out.println("Número de pizzas veganas: " + veganPizzas);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public PizzaEntity get(int idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }


    public boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    public List<PizzaEntity> getAll() {
        return this.jdbcTemplate.query(
                "SELECT * FROM pizza",
                new BeanPropertyRowMapper<>(PizzaEntity.class)
        );
    }

    public List<PizzaEntity> getUnavailablePizzas() {
        return this.jdbcTemplate.query(
                "SELECT * FROM pizza WHERE available = 0",
                new BeanPropertyRowMapper<>(PizzaEntity.class)
        );
    }

}
