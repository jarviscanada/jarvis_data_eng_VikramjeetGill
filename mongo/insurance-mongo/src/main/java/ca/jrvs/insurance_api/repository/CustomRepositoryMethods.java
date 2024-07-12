package ca.jrvs.insurance_api.repository;

import ca.jrvs.insurance_api.model.Person;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomRepositoryMethods {

    void saveAll(List<Person> persons);
    List<Person> findAllByIds(List<ObjectId> ids);
    void delete(ObjectId id);
    void update(Person person);
    void update(List<Person> persons);
    double getAverageAge();
    int getMaxCars();
    void deleteAll();

}
