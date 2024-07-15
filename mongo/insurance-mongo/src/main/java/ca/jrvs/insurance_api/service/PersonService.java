package ca.jrvs.insurance_api.service;

import ca.jrvs.insurance_api.model.Person;
import ca.jrvs.insurance_api.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    // @Autowired // Might not need this tag
    private final PersonRepository repo;

    public PersonService(PersonRepository repo){
        this.repo = repo;
    }

    public void save(Person person){
        repo.save(person);
    }

    public void saveAll(List<Person> people) {
        repo.saveAll(people);
    }

    public Optional<Person> findOne(String id) {
        Optional<Person> result = repo.findById(id); // I might need to check this later for errors if theres something wrong with the conversion of id to string.
        return result;
    }

    public List<Person> findAll(List<ObjectId> ids) {
        List<Person> result = repo.findAllByIds(ids);
        return result;
    }

    public List<Person> findAll() {
        List<Person> result = repo.findAll();
        return result;
    }

    public void delete(ObjectId id) {
        repo.delete(id);
    }

    public void delete(List<ObjectId> ids) {
        for (ObjectId id : ids) {
            repo.delete(id);
        }
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public void update(Person person) {
        repo.update(person);
    }

    public void update(List<Person> people) {
        repo.update(people);
    }

    public long count() {
        long result = repo.count();
        return result;
    }

    public double getAverageAge() {
        double result = repo.getAverageAge();
        return result;
    }

    public int getMaxCars() {
        int result = repo.getMaxCars();
        return result;
    }

}
