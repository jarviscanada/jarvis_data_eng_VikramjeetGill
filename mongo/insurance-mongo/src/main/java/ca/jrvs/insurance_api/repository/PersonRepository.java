package ca.jrvs.insurance_api.repository;
import ca.jrvs.insurance_api.model.Person;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends MongoRepository<Person, String>, CustomRepositoryMethods {

    // The below methods will be implemented automatically by Spring Data, so I just need to declare them.

    Person save(Person person);
    long count();
    Optional<Person> findById(String id);
    List<Person> findAll();
}
