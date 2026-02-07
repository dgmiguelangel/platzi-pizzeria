package com.platzi.pizza.persistence.revision;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Entity
//@Table(name = "pizza_info")
//@RevisionEntity(RevisionPizzaListener.class)
@Getter
@Setter
public class RevisionPizza implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int id;

    @Column(name = "revision_date")
    @RevisionTimestamp
    private LocalDateTime date;

    @Column(name = "user_name")
    private String userName;

}