package com.testcase.skyeng.controllers;

import com.testcase.skyeng.models.Person;
import com.testcase.skyeng.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Person> index() {
        return service.index();
    }

    @PostMapping("/")
    public Person newItem(Person item) {
        return service.saveItem(item);
    }


    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id) {
        return service.getBuIdOrNull(id);
    }

    @PutMapping("/{id}")
    public Person updateItem(@PathVariable Long id, Person item) {
        return service.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public boolean delById(@PathVariable Long id) {
        return service.delById(id);
    }
}
