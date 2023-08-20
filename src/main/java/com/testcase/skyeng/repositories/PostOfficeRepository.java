package com.testcase.skyeng.repositories;

import com.testcase.skyeng.models.PostOffice;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostOfficeRepository extends CommonRepository<PostOffice> {
    Optional<PostOffice> findByNameAndIndex(String name, int index);
}
