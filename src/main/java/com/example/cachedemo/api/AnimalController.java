
package com.example.cachedemo.api;

import com.example.cachedemo.model.Animal;
import com.example.cachedemo.repos.AnimalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author claus
 */
@RestController
@Log
@RequiredArgsConstructor
public class AnimalController {
    
    private final AnimalRepository repo;
    
    @PostMapping("/animal")
    public void saveAnimal(@RequestBody Animal animal) {
        this.repo.save(animal);
    }
    
    @GetMapping("/animal/:id")
    public Animal readAnimal(@PathVariable("id") String id){
//        this.repo.

        return null;
    }
    
}
