package com.platzi.pizza.persistence.revision;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class RevisionPizzaListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        final RevisionPizza revision = (RevisionPizza) revisionEntity;
        revision.setUserName(getThreadAccountUserName());
    }

    private String getThreadAccountUserName() {
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }

        return "NOT_FOUND";
    }

}
