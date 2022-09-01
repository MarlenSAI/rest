package com.springframework.saiedmadminpanel.web.services;
import com.springframework.saiedmadminpanel.web.model.Person;
import com.springframework.saiedmadminpanel.web.repositories.PersonRepository;
import com.springframework.saiedmadminpanel.web.util.PersonErrorResponse;
import com.springframework.saiedmadminpanel.web.util.PersonNotCreatedException;
import com.springframework.saiedmadminpanel.web.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }


}
