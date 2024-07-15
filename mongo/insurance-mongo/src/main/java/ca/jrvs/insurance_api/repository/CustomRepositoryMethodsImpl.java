package ca.jrvs.insurance_api.repository;

import ca.jrvs.insurance_api.model.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.List;

/*
This class implements the custom methods declared in the CustomRepositoryMethods interface.
 */

@Repository
public class CustomRepositoryMethodsImpl implements CustomRepositoryMethods{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveAll(List<Person> persons) {
        mongoTemplate.insertAll(persons);
    }

    @Override
    public List<Person> findAllByIds(List<ObjectId> ids) {
        return mongoTemplate.find(query(where("_id").in(ids)), Person.class);
    }

    @Override
    public void delete(ObjectId id) {
        mongoTemplate.remove(query(where("_id").is(id)), Person.class);
    }

    @Override
    public void update(Person person) {
        mongoTemplate.save(person);
    }

    @Override
    public void update(List<Person> persons) {
        for (Person person : persons) {
            mongoTemplate.save(person);
        }
    }

    @Override
    public double getAverageAge() {
        int temp = 0;
        List<Person> persons = mongoTemplate.findAll(Person.class, "people");
        for (Person person : persons) {
            temp += person.getAge();
        }
        return (double) temp / persons.size();
    }

    @Override
    public int getMaxCars() {
        List<Person> persons = mongoTemplate.findAll(Person.class, "people");
        Person personWithMaxCars = persons.get(0);
        for (Person person : persons) {
            if (person.getCarEntities().size() > personWithMaxCars.getCarEntities().size()) {
                personWithMaxCars = person;
            }
        }
        return personWithMaxCars.getCarEntities().size();
    }

    @Override
    public void deleteAll(){
        mongoTemplate.remove(new Query(), Person.class);
    }
}
