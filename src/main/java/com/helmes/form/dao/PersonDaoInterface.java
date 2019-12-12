package com.helmes.form.dao;


import com.helmes.form.model.Person;

import java.util.List;

public interface PersonDaoInterface {

    void save(Person person);

    List<Person> findAll();

    Person findPersonByName(String firstName, String lastName);

    Person findPersonById(Integer id);

    void update(Person person);

    void delete(Integer id);
}
