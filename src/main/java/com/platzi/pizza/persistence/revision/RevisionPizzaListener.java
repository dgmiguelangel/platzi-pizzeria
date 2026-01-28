package com.platzi.pizza.persistence.revision;

import org.hibernate.envers.RevisionListener;

public class RevisionPizzaListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        final RevisionPizza revision = (RevisionPizza) revisionEntity;
        revision.setUserName(getThreadAccountUserName());
    }

    private String getThreadAccountUserName() {
        if(System.getProperty("user.name") != null) {
            return System.getProperty("user.name");
        }
        return "NOT_FOUND";

        /*
        if (AccountThreadContext.getAccount() != null){
            return AccountThreadContext.getAccount().getCode();
        }
        */
    }

}
