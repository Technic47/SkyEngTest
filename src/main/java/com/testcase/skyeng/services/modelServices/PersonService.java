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

    /**
     * Check existence of Person in DB and save if not.
     *
     * @param person Person to check
     * @return saved Person
     */
    public Person checkByPassport(Person person) {
        Optional<Person> findPerson = repository.findByPassportNumber(person.getPassportNumber());
        return findPerson.orElseGet(() -> saveItem(person));
    }

    /**
     * Add address to Person
     *
     * @param id      id of Person
     * @param address Address to add
     * @return saved Person
     */
    public Person addAddress(Long id, Address address) {
        Optional<Person> item = repository.findById(id);
        if (item.isPresent()) {
            Person person = item.get();
            person.setAddress(address);
            return repository.save(person);
        } else return null;
    }
}