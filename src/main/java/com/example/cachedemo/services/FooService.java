package com.example.cachedemo.services;

import com.example.cachedemo.model.Animal;
import com.example.cachedemo.model.Keeper;
import com.example.cachedemo.repos.AnimalRepository;
import com.example.cachedemo.repos.KeeperRepository;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author claus
 */
@Service
@CacheConfig(cacheNames = {"KEEPER_CACHE"})
@Log
@NoArgsConstructor
//@RequiredArgsConstructor
public class FooService {
    
    @Autowired
    AnimalRepository animalRepo;
    @Autowired
    KeeperRepository keeperRepo; 
    @Autowired
    EntityManager em;
    
    @Cacheable(key = "#p0")
    public Keeper findKeeper(String oid) {
        log.info("getting new keeper...");
        return new Keeper("Bernd");
    }
    
    @Transactional
    public Animal saveAnimalWithRef(String animalName, UUID... keeperUuids) {
        List<Keeper> keepers = Lists.newArrayList();
        for (UUID keeperUuid : keeperUuids) {
            keepers.add(this.keeperRepo.findOne(keeperUuid));
        }
        Animal animal = new Animal(animalName, keepers);
        animal.setOid(UUID.randomUUID());
        return this.animalRepo.save(animal);
    }
    
}
