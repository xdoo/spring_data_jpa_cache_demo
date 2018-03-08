
package com.example.cachedemo.repos;

import com.example.cachedemo.CacheDemoApplication;
import com.example.cachedemo.model.Keeper;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author claus
 */
@CacheConfig(cacheNames = {"KEEPER_CACHE"})
public interface KeeperRepository extends CrudRepository<Keeper, UUID> {
    
    static final Logger LOGGER = Logger.getLogger(CacheDemoApplication.class.getName());

    @Override
    @CachePut(key = "#p0.oid")
    public <S extends Keeper> S save(S s);

    @Override
    @Cacheable(key = "#p0")
    public Keeper findOne(UUID oid);
    
    
}
