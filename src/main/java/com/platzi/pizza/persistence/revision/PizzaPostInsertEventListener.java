package com.platzi.pizza.persistence.revision;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
import org.hibernate.event.spi.PostInsertEvent;

public class PizzaPostInsertEventListener extends EnversPostInsertEventListenerImpl {

    public PizzaPostInsertEventListener(EnversService enversService) {
        super(enversService);
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        super.onPostInsert(event);
    }
}
