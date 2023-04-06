package com.example.ejercicio101112.controller;

import com.example.ejercicio101112.entity.Laptop;
import com.example.ejercicio101112.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class LaptopController {

    LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping(value="/laptos")
    public List<Laptop> laptos() {
        return laptopRepository.findAll();
    }

    @GetMapping(value="/lapto/{id}")
    public ResponseEntity<Laptop> unLaptop(@PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);
        if(laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping(value="/laptos")
    public ResponseEntity<Laptop> crear(@RequestBody Laptop laptop){
        if(laptop.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        Laptop resultado= laptopRepository.save(laptop);
        return ResponseEntity.ok(resultado);
    }


    @PutMapping(value = "/laptos")
    public ResponseEntity<Laptop> actualizar(@RequestBody Laptop laptop){
        if(laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())) {
            return ResponseEntity.notFound().build();
        }
        Laptop resultado = laptopRepository.save(laptop);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Laptop> eliminarUno(@PathVariable Long id){
        if(!laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/eliminar")
    public ResponseEntity<Laptop> eliminarTodos(){
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
