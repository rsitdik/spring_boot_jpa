package com.itvdn.aspects;

import com.itvdn.exceptions.NotAuthorizedException;
import com.itvdn.model.Authorization;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {
    private Authorization auth;

//    @Before("pointCut())")
    public void checkAuthorize() {
        if (!auth.getAuthorized()) {
            throw new NotAuthorizedException("User is not authorized!");
        }
    }

    @Pointcut("pointAll() && pointAuthorize() && pointUnAuthorize() && pointHelloPage()")
    public void pointCut() {
    }

    @Pointcut("execution(* com.itvdn.controller.AppController.*(..))")
    public void pointAll() {
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

    @Autowired
    public void setAuth(Authorization auth) {
        this.auth = auth;
    }
}
