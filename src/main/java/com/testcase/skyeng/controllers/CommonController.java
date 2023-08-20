package com.testcase.skyeng.controllers;

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
        T item = service.getByIdOrNull(id);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        return item;
    }

    @PutMapping("/{id}")
    public T updateItem(@PathVariable Long id, @RequestBody T item) {
        return service.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public boolean delById(@PathVariable Long id) {
        return service.delById(id);
    }
}
