package com.example.cachedemo.services;

import com.example.cachedemo.model.Keeper;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author claus
 */
@Service
@CacheConfig(cacheNames = {"KEEPER_CACHE"})
@Log
public class FooService {
    
    @Cacheable(key = "#p0")
    public Keeper findKeeper(String oid) {
        log.info("getting new keeper...");
        return new Keeper("Bernd");
    }
    
}
