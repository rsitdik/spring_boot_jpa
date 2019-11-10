package com.itvdn.config;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@EnableCaching
public class CachingConfig {

    @Bean(name = "cacheManager")
    public SimpleCacheManager simpleCacheManager(Set<Cache> caches) {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

    @Bean(name = "employees")
    public ConcurrentMapCacheFactoryBean getEmployeesCache() {
        return new ConcurrentMapCacheFactoryBean();
    }

    @Bean(name = "empl")
    public ConcurrentMapCacheFactoryBean getEmplCache() {
        return new ConcurrentMapCacheFactoryBean();
    }

}
