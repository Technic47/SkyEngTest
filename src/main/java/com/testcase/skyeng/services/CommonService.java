package com.testcase.skyeng.services;

import com.testcase.skyeng.models.additions.CommonEntity;
import com.testcase.skyeng.repositories.CommonRepository;

import java.util.List;
import java.util.Optional;

public abstract class CommonService<T extends CommonEntity, R extends CommonRepository<T>> implements CommonInterface<T>{
    protected R repository;

    public CommonService(R repository) {
        this.repository = repository;
    }

    public List<T> index() {
        return repository.findAll();
    }

    public T saveItem(T item) {
        return repository.save(item);
    }

    public T getBuIdOrNull(Long id) {
        Optional<T> item = repository.findById(id);
        return item.orElse(null);
    }

    public T updateItem(Long id, T newItem) {
        Optional<T> item = repository.findById(id);
        if (item.isPresent()) {
            T oldItem = item.get();
            oldItem.copy(newItem);
            return repository.save(oldItem);
        }
        return repository.save(newItem);
    }

    public boolean delById(Long id) {
        Optional<T> item = repository.findById(id);
        if (item.isPresent()) {
            repository.delete(item.get());
            return true;
        } else return false;
    }
}
