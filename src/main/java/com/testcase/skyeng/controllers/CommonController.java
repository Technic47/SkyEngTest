package com.testcase.skyeng.controllers;

import com.testcase.skyeng.models.additions.CommonEntity;
import com.testcase.skyeng.services.CommonInterface;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CommonController<T extends CommonEntity,
        S extends CommonInterface<T>> {
    private final S service;

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
        return service.getBuIdOrNull(id);
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
