package com.example.cachedemo.repos;

import com.example.cachedemo.CacheDemoApplication;
import com.example.cachedemo.model.Animal;
import java.util.logging.Logger;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author claus
 */
public interface AnimalRepository extends CrudRepository<Animal, String> {
    
    static final Logger LOGGER = Logger.getLogger(CacheDemoApplication.class.getName());
    final String CACHE = "ANIMAL_CACHE";

    @Override
    @CachePut(value = CACHE, key = "#p0.oid")
    public <S extends Animal> S save(S s);

    @Override
    @CachePut(value = CACHE, key = "#p0")
    public Animal findOne(String oid);
   
    
    
    
}
