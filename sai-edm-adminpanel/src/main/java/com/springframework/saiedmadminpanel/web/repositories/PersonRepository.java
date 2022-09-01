package com.springframework.saiedmadminpanel.web.repositories;

import com.springframework.saiedmadminpanel.web.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
