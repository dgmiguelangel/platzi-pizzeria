package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final JdbcTemplate jdbcTemplate;

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate, PizzaRepository pizzaRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pizzaRepository = pizzaRepository;
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
