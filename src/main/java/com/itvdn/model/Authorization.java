package com.itvdn.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class Authorization {
    private Boolean authorized = Boolean.FALSE;

    public Authorization() {
    }

    public Boolean getAuthorized() {
        return authorized;
    }

    public Authorization setAuthorized(Boolean authorized) {
        this.authorized = authorized;
        return this;
    }
}
