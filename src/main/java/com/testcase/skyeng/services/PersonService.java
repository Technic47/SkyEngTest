package com.testcase.skyeng.services;

import com.testcase.skyeng.models.Person;
import com.testcase.skyeng.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService extends CommonService<Person, PersonRepository>{

    public PersonService(PersonRepository repository) {
        super(repository);
    }
}
