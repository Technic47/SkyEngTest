package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.Person;
import com.testcase.skyeng.repositories.PersonRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService extends CommonService<Person, PersonRepository> {
    public PersonService(PersonRepository repository) {
        super(repository);
    }

    public Person findByFirstAndSecondName(Person person){
        Optional<Person> findPerson = repository.findByFirstNameAndSecondName(person.getFirstName(), person.getSecondName());
        return findPerson.orElseGet(() -> saveItem(person));
    }

    public Person findByPassport(Person person){
        Optional<Person> findPerson = repository.findByPassportNumber(person.getPassportNumber());
        return findPerson.orElseGet(() -> saveItem(person));
    }

    public Person addAddress(Long id, Address address) {
        Optional<Person> item = repository.findById(id);
        if (item.isPresent()) {
            Person person = item.get();
            person.setAddress(address);
            return repository.save(person);
        } else return null;
    }
}