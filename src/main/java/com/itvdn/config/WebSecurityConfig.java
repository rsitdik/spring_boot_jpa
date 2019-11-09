package com.itvdn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.PropertyResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@PropertySource(value = "classpath:application-dev.properties")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private PropertyResolver environment;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/rest/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/employee/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')" )
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/", false)
                .failureForwardUrl("/login?error=true")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .and()
                .rememberMe();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl dao = new JdbcDaoImpl();
        dao.setDataSource(dataSource());
        dao.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        dao.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return dao;
    }

    // in memory authentication, non production
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("employee1").password("{noop}1").roles("EMPLOYEE");
//        auth.inMemoryAuthentication().withUser("employee2").password("{noop}2").roles("EMPLOYEE");
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setEnvironment(PropertyResolver environment) {
        this.environment = environment;
    }
}
