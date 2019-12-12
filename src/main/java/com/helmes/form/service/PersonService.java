package com.helmes.form.service;

import com.helmes.form.dao.PersonDaoInterface;
import com.helmes.form.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements PersonServiceInterface {

    PersonDaoInterface personDao;

    @Autowired
    public void setPersonDao(PersonDaoInterface personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person findPersonById(Integer id) {
        return personDao.findPersonById(id);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public void saveOrUpdate(Person person) {
        if (findPersonById(person.getId()) == null) {
            personDao.save(person);
        } else {
            personDao.update(person);
        }
    }

    @Override
    public void delete(int id) {
        personDao.delete(id);
    }

}
