package com.example.Ejercicios456.controllers;

import com.example.Ejercicios456.entitys.Laptop;
import com.example.Ejercicios456.repositorys.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {



    private Logger logger = LoggerFactory.getLogger(LaptopController.class);
    private LaptopRepository repository;

    public LaptopController(LaptopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Laptop/findall")
    public List<Laptop> findAll(){
        return repository.findAll();
    }

    @GetMapping("/Laptop/findbyid/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptopOptional = repository.findById(id);

        if(laptopOptional.isPresent()){
            return ResponseEntity.ok(laptopOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Laptop/save")
    public ResponseEntity<Laptop> create(@RequestBody Laptop l1){

        if (l1.getId() != null){
            logger.warn("Do not give id for new laptop");
            return ResponseEntity.badRequest().build();
        }

        Laptop laptop = repository.save(l1);
        return ResponseEntity.ok(laptop);
    }

    @PutMapping("/Laptop/update")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){

        if (laptop.getId() == null){
            logger.warn("No id inputed");
            return ResponseEntity.badRequest().build();
        }

        if (!repository.existsById(laptop.getId())){
            logger.warn("The id given does not exist");
            return ResponseEntity.notFound().build();
        }

        Laptop l1 = repository.save(laptop);
        return ResponseEntity.ok(l1);
    }

    @DeleteMapping("/Laptop/delete/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if (!repository.existsById(id)){
            logger.warn("Trying to delete a non existant Laptop");
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/Laptop/delete/all")
    public ResponseEntity<Laptop> deleteAll(){
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
