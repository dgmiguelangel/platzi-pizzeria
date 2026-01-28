package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    Optional<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name);

    int countByVeganTrue();

    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    // SPEL - Spring Expression Language. Usado para mapear los campos del DTO a los parámetros de la consulta
    // Modifying es necesario para las consultas de actualización, eliminación o inserción (nativas o JPQL)
    @Query(value = """
        UPDATE pizza
        SET price = :#{#newPizzaPrice.newPrice}
        WHERE id_pizza = :#{#newPizzaPrice.pizzaId}
     """, nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);

    @Query(value = """
        UPDATE pizza
        SET price = :newPrice
        WHERE id_pizza = :idPizza
     """, nativeQuery = true)
    void updatePrice(@Param("idPizza") int idPizza, @Param("newPrice") double newPrice);

}