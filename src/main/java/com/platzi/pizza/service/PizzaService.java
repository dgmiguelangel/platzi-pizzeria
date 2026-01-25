package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final JdbcTemplate jdbcTemplate;

    private final PizzaRepository pizzaRepository;

    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(JdbcTemplate jdbcTemplate, PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy) {
        // Pageable pageRequest = PageRequest.of(page, elements, Sort.by("price").ascending().and(Sort.by("idPizza").descending()));
        // Pageable pageRequest = PageRequest.of(page, elements, Sort.by(Sort.Direction.ASC, "price", "idPizza"));
        // Pageable pageRequest = PageRequest.of(page, elements, Sort.by(Sort.Order.asc(sortBy)));
        Pageable pageRequest = PageRequest.of(page, elements, Sort.by(sortBy));
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
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
