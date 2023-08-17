package com.testcase.skyeng.services;

import com.testcase.skyeng.models.additions.CommonEntity;

import java.util.List;

public interface CommonInterface<T extends CommonEntity> {
    List<T> index();
    T saveItem(T item);
    T getBuIdOrNull(Long id);
    T updateItem(Long id, T newItem);
    boolean delById(Long id);
}
