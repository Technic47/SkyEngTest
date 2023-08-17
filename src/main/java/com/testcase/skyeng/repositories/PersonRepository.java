package com.testcase.skyeng.repositories;

import com.testcase.skyeng.models.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CommonRepository<Person> {
}
