package com.itvdn.aspects;

import com.itvdn.model.Authorization;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component
@Aspect()
public class MyLogger {
    private ApplicationContext applicationContext;

    @Pointcut("execution(* com.itvdn.controller.AppController.*(..))")
    public void service() {
    }

    @Before("service()")
    public void beforeMethodInvocation(JoinPoint joinPoint) {
        Authorization authorization = applicationContext.getBean("authorization", Authorization.class);
        out.println("Authorized: " + authorization.getAuthorized());
        out.println("********************************************");
        out.println("Log : before method " + joinPoint.getSignature().toShortString() + ".");
        out.println("********************************************");
    }

    @AfterReturning(value = "execution(* com.itvdn.jpa.EmployeeRepository.*(..)) && args(result)")
    public void afterMethodInvocation(JoinPoint joinPoint, Object result) {
        out.println(joinPoint.getSignature().getName() + " method of "
                + joinPoint.getTarget().toString() + " was invoked!");
        out.println("Result of method execution : " + result);
        out.println("********************************************");
        out.println("***** Log : success method invocation ******");
        out.println("********************************************");
    }

    @AfterThrowing(throwing = "ex", pointcut = "within(@org.springframework.web.bind.annotation.RequestMapping *)")
    public void afterThrowing(Exception ex) {
        out.println("***************** Log : error ***************");
        out.println("Exception: " + ex.getMessage());
        out.println("********************************************");
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
