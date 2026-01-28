package com.platzi.pizza.persistence.revision;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistryEnversListenerConfiguration {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @PostConstruct
    protected void init(){
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        EnversService enversService = sessionFactory.getServiceRegistry().getService(EnversService.class);

        assert registry != null;
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListeners(new PizzaPostInsertEventListener(enversService));
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListeners(new PizzaPreUpdateEventListener(enversService));
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListeners(new PizzaPostUpdateEventListener(enversService));
    }

}
