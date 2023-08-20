package com.testcase.skyeng.controllers;

import com.testcase.skyeng.exceptions.ResourceNotFoundException;
import com.testcase.skyeng.models.additions.CommonEntity;
import com.testcase.skyeng.services.CommonInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public abstract class CommonController<T extends CommonEntity,
        S extends CommonInterface<T>> {
    protected final S service;

    protected CommonController(S service) {
        this.service = service;
    }

    @GetMapping()
    public List<T> index() {
        return service.index();
    }

    @PostMapping()
    public T newItem(@RequestBody T newItem) {
        return service.saveItem(newItem);
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable Long id) {
        try {
            return service.getById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public T updateItem(@PathVariable Long id, @RequestBody T item) {
        return service.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public boolean delById(@PathVariable Long id) {
        try {
            return service.delById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
