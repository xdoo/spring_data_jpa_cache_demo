package com.example.cachedemo;

import java.util.Arrays;
import lombok.extern.java.Log;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author claus
 */
@Configuration
@EnableCaching
@Log
public class CacheConfig {
    
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
          new ConcurrentMapCache("KEEPER_CACHE"), 
          new ConcurrentMapCache("ANIMAL_CACHE")));
        
        log.info("Initialized Cache Manager.");
        
        return cacheManager;
    }
    
}
