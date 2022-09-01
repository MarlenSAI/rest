package com.springframework.saiedmadminpanel.web.controller;


import com.springframework.saiedmadminpanel.web.model.Person;
import com.springframework.saiedmadminpanel.web.services.PersonService;
import com.springframework.saiedmadminpanel.web.util.PersonErrorResponse;
import com.springframework.saiedmadminpanel.web.util.PersonNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/users/{users}")
    public List<Person> getPeople(){
        return personService.findAll();
    }

    @GetMapping("/user/{id}")
    public Person getPerson(@PathVariable("id") int id){
        System.out.println(1);
        return personService.findOne(id);
    }

    @PostMapping({"/users"})
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(" ; ");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        personService.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonErrorResponse e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Пользователь с таким айди не существует",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
