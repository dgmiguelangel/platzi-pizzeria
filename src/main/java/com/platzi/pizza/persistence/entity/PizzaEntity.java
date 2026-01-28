package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.io.Serializable;

//@EntityListeners(AuditingEntityListener.class)
@Audited
@AuditTable(value = "pizza_audit")
@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PizzaEntity implements Serializable {
    //public class PizzaEntity extends AuditableEntity implements Serializable {

    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

    /*
    @Override
    public String toString() {
        return "PizzaEntity {" +
                "idPizza= " + idPizza +
                ", name= '" + name + '\'' +
                ", description= '" + description + '\'' +
                ", price= " + price +
                ", vegetarian= " + vegetarian +
                ", vegan= " + vegan +
                ", available= " + available +
                '}';
    }
    */

    /*
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    */

}
