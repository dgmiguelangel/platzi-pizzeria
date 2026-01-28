package com.platzi.pizza.persistence.audit;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.*;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentValue;

    // Se ejecuta después de que una entidad ha sido cargada desde la base de datos
    @PostLoad
    public void onPostLoad(PizzaEntity entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("POST PERSIST");
        System.out.println("NEW VALUE: " + entity.toString());
    }

    @PreUpdate
    public void onPreUpdate(PizzaEntity entity) {
        System.out.println("PRE UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + entity.toString());
    }

    @PostUpdate
    public void onPostUpdate(PizzaEntity entity) {
        System.out.println("POST UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + entity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println("PRE DELETE");
        System.out.println(entity.toString());
    }

}
