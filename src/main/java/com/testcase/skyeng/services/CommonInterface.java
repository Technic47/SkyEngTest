package com.testcase.skyeng.services;

import com.testcase.skyeng.exceptions.ResourceNotFoundException;
import com.testcase.skyeng.models.additions.CommonEntity;

import java.util.List;

public interface CommonInterface<T extends CommonEntity> {
    List<T> index();
    T saveItem(T item);
    T getById(Long id) throws ResourceNotFoundException;
    T updateItem(Long id, T newItem);
    boolean delById(Long id) throws ResourceNotFoundException;
}
