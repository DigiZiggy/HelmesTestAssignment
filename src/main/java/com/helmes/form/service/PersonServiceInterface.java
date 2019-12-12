package com.helmes.form.service;

import com.helmes.form.model.Person;

import java.util.List;

public interface PersonServiceInterface {

    Person findPersonById(Integer id);

    List<Person> findAll();

    void saveOrUpdate(Person person);

    void delete(int id);
}
