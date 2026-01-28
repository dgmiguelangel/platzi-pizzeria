package com.platzi.pizza.persistence.revision;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPreUpdateEventListenerImpl;
import org.hibernate.event.spi.PreUpdateEvent;

public class PizzaPreUpdateEventListener extends EnversPreUpdateEventListenerImpl {

    public PizzaPreUpdateEventListener(EnversService enversService) {
        super(enversService);
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        return super.onPreUpdate(event);
    }
}
