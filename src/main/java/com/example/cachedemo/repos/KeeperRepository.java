
package com.example.cachedemo.repos;

import com.example.cachedemo.CacheDemoApplication;
import com.example.cachedemo.model.Keeper;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author claus
 */
public interface KeeperRepository extends CrudRepository<Keeper, UUID> {
    
    static final Logger LOGGER = Logger.getLogger(CacheDemoApplication.class.getName());
    final String CACHE = "KEEPER_CACHE";

    @Override
    @CachePut(value = CACHE, key = "#p0.oid")
    public <S extends Keeper> S save(S s);

    @Override
    @CachePut(value = CACHE, key = "#p0")
    public Keeper findOne(UUID oid);
    
    
}
