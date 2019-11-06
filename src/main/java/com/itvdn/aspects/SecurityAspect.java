package com.itvdn.aspects;

import com.itvdn.exceptions.NotAuthorizedException;
import com.itvdn.model.Authorization;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect()
public class SecurityAspect {
    private Authorization auth;

    @Before("points())")
    public void checkAuthorize() {
        if (!auth.getAuthorized()) {
            throw new NotAuthorizedException("User is not authorized!");
        }
    }

    @Autowired
    public void setAuth(Authorization auth) {
        this.auth = auth;
    }

    @Pointcut("point() && pointAuthorize() && pointUnAuthorize() && pointHelloPage()" )
    public void points() {
    }

    @Pointcut("execution(* com.itvdn.controller.AppController.*(..))")
    public void point() {
    }

    @Pointcut(value = "!execution(* com.itvdn.controller.AppController.authorize(..))")
    public void pointAuthorize() {
    }

    @Pointcut("!execution(* com.itvdn.controller.AppController.unAuthorize(..))")
    public void pointUnAuthorize() {
    }

    @Pointcut("!execution(* com.itvdn.controller.AppController.helloPage(..))")
    public void pointHelloPage() {
    }
}
