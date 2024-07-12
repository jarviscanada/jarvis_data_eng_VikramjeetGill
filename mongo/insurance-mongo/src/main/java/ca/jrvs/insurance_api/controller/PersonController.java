package ca.jrvs.insurance_api.controller;

import ca.jrvs.insurance_api.model.Person;
import ca.jrvs.insurance_api.service.PersonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("insurance_api")
public class PersonController {
    
    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    // This method saves an individual person to the collection

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody Person person) {
        service.save(person);
    }

    // This method retrieves a list of everyone in the people collection

    @GetMapping("/people")
    public List<Person> getPeople() {
        return service.findAll();
    }

    // This method retrieves a person from the people collection based on the id

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable String id) {
        Optional<Person> o = service.findOne(id);
        if (o.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(o.get());
    }

    // This method saves all the people from a list of people to the people collection

    @PostMapping("/people")
    public void postPeople(@RequestBody List<Person> people) {
        service.saveAll(people);
    }

    // This method returns a list of people from the collection people whose id match one of the ids in the provided list

    @GetMapping("/people/{ids}")
    public List<Person> getPeopleByIds(@PathVariable List<ObjectId> ids) {
        return service.findAll(ids);
    }

    // This method deletes a person from the collection people whose id matches the provided id

    @DeleteMapping("/person/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable ObjectId id) {
        service.delete(id);
    }

    // This method deletes people from the collection people whose id match one of the ids provided in the list

    @DeleteMapping("/people")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePeople(@RequestBody List<ObjectId> ids) {
        service.delete(ids);
    }

    // This method deletes all people from the collection people

    @DeleteMapping("/people/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPeople() {
        service.deleteAll();
    }

    // This method updates a person

    @PutMapping("/person")
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(@RequestBody Person person) {
        service.update(person);
    }

    // This method updates a list of people in the collection people

    @PutMapping("/people")
    @ResponseStatus(HttpStatus.OK)
    public void updatePeople(@RequestBody List<Person> people) {
        service.update(people);
    }

    // This method returns a count of the total number of people in the collection people

    @GetMapping("/people/count")
    public long countPeople() {
        return service.count();
    }

    // This method returns the average age of all people in the collection people

    @GetMapping("/people/average-age")
    public double getAverageAge() {
        return service.getAverageAge();
    }

    // This method returns the largest number of cars associated with any person in the collection people.

    @GetMapping("/people/max-cars")
    public int getMaxCars() {
        return service.getMaxCars();
    }

}
