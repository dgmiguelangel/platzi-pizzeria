package com.platzi.pizza.persistence.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

// Clase base para entidades que requieren auditoría de fechas de creación y modificación
// MappedSuperclass No crea una tabla propia en la base de datos
@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class, AuditPizzaListener.class })
public class AuditableEntity {

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime modifiedDate;

    /*
    @Column(name = "created_by", updatable = false)
    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    @JsonIgnore
    private String modifiedBy;
    */

}