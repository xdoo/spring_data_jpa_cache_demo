package com.example.cachedemo;

import com.example.cachedemo.model.Animal;
import com.example.cachedemo.model.Keeper;
import com.example.cachedemo.repos.AnimalRepository;
import com.example.cachedemo.repos.KeeperRepository;
import com.example.cachedemo.services.FooService;
import com.google.common.collect.Lists;
import java.util.UUID;
import lombok.extern.java.Log;
import static org.apache.tomcat.jni.Lock.name;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class CacheDemoApplicationTests {

    @Autowired
    AnimalRepository animalRepo;

    @Autowired
    KeeperRepository keeperRepo;
    
    @Autowired
    FooService service;

//    @Test
    public void saveAnimal() {
        Animal animal = this.createAnimal();
        Animal saved = this.animalRepo.save(animal);
        this.checkSave();
        for (int i = 0; i < 100; i++) {
            Animal find = this.animalRepo.findOne(saved.getOid());
            assertNotNull(find);
        }
    }
    
    @Test
    public void testReferenceKeeper() {
        Keeper torben = this.keeperRepo.save(new Keeper("Torben"));
        Animal nemo = this.animalRepo.save(new Animal("Nemo", Lists.newArrayList(torben)));
        assertNotNull(nemo);
        assertEquals(1, this.animalRepo.count());
        assertEquals(1, this.keeperRepo.count());
        Keeper hans = this.keeperRepo.save(new Keeper("Hans"));
        Keeper peter = this.keeperRepo.save(new Keeper("Peter"));
        Keeper maja = this.keeperRepo.save(new Keeper("Maja"));
        assertEquals(4, this.keeperRepo.count());
        peter.setName("Petra");
        this.keeperRepo.save(peter);
        assertEquals(4, this.keeperRepo.count());
    }

//    @Test
    public void testSaveAnimalWithKeepersOverFooServiceNotAutocreatedID() {
        Keeper torben = this.keeperRepo.save(this.createKeeper("Torben"));
        Keeper malte = this.keeperRepo.save(this.createKeeper("Malte"));
        Animal dumbo = this.service.saveAnimalWithRef("Dumbo", torben.getOid(), malte.getOid());
        assertNotNull(dumbo);
    }
    
//    @Test
    public void testSaveAnimalWithKeepersOverRepositoryNotAutocreatedID() {
        Keeper torben = this.keeperRepo.save(this.createKeeper("Torben"));
        Keeper malte = this.keeperRepo.save(this.createKeeper("Malte"));
        Animal dumbo = this.animalRepo.save(this.createAnimal("dumbo", torben, malte));
        assertNotNull(dumbo);
    }
    
//    @Test
    public void findKeeperInService() {
        for (int i = 0; i < 100; i++) {
            this.service.findKeeper("4711");
        }
    }
    
    private Keeper createKeeper(String name) {
        Keeper keeper = new Keeper(name);
        keeper.setOid(UUID.randomUUID());
        return keeper;
    }

    private Animal createAnimal() {
        return new Animal("Bagira", Lists.newArrayList(
                new Keeper("Hans"),
                new Keeper("Peter")
        ));
    }
    
    private Animal createAnimal(String name, Keeper... keepers) {
        Animal animal = new Animal(name, Lists.newArrayList(keepers));
        animal.setOid(UUID.randomUUID());
        return animal;
    }

    private void checkSave() {

        assertEquals(1, this.animalRepo.count());
        assertEquals(2, this.keeperRepo.count());

//        Animal bagira = this.animalRepo.findOne(UUID.fromString(OID_BAGIRA));
//        log.info(bagira.toString());
//        assertNotNull(bagira);
//        assertNotNull(this.keeperRepo.findOne(UUID.fromString(OID_HANS)));
//        assertNotNull(this.keeperRepo.findOne(UUID.fromString(OID_PETER)));
    }

}
