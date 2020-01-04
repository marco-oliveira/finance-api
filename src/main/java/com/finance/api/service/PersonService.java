package com.finance.api.service;

import com.finance.api.model.Person;
import com.finance.api.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person update(Long id, Person person) {
        Person personSaved = findById(id);
        BeanUtils.copyProperties(person, personSaved, "id");
        return this.personRepository.save(personSaved);
    }


    public void updatePropertyActive(Long id, Boolean active) {
        Person person = findById(id);
        person.setActive(active);
        this.personRepository.save(person);
    }

    public Person findById(Long id) {
        return this.personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
