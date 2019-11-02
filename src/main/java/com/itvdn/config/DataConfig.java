package com.itvdn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = "classpath:util.properties")
@EnableTransactionManagement
public class DataConfig {
    // PropertyResolver
//    private Environment environment;

    //настройки для подключения к БД,   spring-orm:5.1.8.RELEASE.jar
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
//        dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
//        dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
//        dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
//        return dataSource;
//    }

    // JdbcTemplate для работы с базой (шаблонный код)
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(dataSource());
//        return jdbcTemplate;
//    }

//    @Bean(name = "entityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setPackagesToScan("com.itvdn.entity", "com.itvdn.*");
//        em.setDataSource(dataSource());
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//        vendorAdapter.setShowSql(true);
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
//        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//
//        em.setJpaProperties(jpaProperties);
//        return em;
//    }

//    @Autowired
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//    }
}
