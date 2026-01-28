package com.platzi.pizza.persistence.revision;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl;
import org.hibernate.event.spi.PostUpdateEvent;

public class PizzaPostUpdateEventListener extends EnversPostUpdateEventListenerImpl {

    public PizzaPostUpdateEventListener(EnversService enversService) {
        super(enversService);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        super.onPostUpdate(event);
    }
}
