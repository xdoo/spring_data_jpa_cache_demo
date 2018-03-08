package com.example.cachedemo;

import com.example.cachedemo.model.Animal;
import com.example.cachedemo.model.Keeper;
import com.example.cachedemo.repos.AnimalRepository;
import com.example.cachedemo.repos.KeeperRepository;
import com.google.common.collect.Lists;
import java.util.UUID;
import lombok.extern.java.Log;
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

    private static final String OID_BAGIRA = "b5af24cc-95bd-4558-9f3c-3899c5b4398d";
    private static final String OID_HANS = "9886dd07-6d99-4699-ac4c-ed107f4aff97";
    private static final String OID_PETER = "5cd67419-daec-4ba3-8784-d3d921a149db";

    @Autowired
    AnimalRepository animalRepo;

    @Autowired
    KeeperRepository keeperRepo;

    @Test
    public void saveAnimal() {
        Animal animal = this.createAnimal();
        Animal saved = this.animalRepo.save(animal);
        this.checkSave();
        for (int i = 0; i < 100; i++) {
            Animal find = this.animalRepo.findOne(saved.getOid());
            assertNotNull(find);
        }
    }

    private Animal createAnimal() {
        return new Animal("Bagira", Lists.newArrayList(
                new Keeper("Hans"),
                new Keeper("Peter")
        ));
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
