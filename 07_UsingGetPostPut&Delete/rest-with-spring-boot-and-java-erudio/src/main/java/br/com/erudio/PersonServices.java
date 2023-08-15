package br.com.erudio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service //Using to Spring Boot see this class wil be inject in runtime in another class in this application
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	
	public Person create(Person person) {
		
		logger.info("Creating one person! ");
		
		return person;
		
	}
	
public Person update(Person person) {
		
		logger.info("Updating one person! ");
		
		return person;
		
	}

public void delete(String id) {
	
	logger.info("deleting one person! ");
	
	
	
}
	
	
	public List<Person> findAll() {
		
		logger.info("Find all people! ");
		List<Person> persons = new ArrayList<>();
		
		for(int i = 0; i<8;i++) {
			
			Person person= mockPerson(i);
			persons.add(person);
		}
		
		return persons;
		
	}

	public Person findByID(String id) {
		
		logger.info("Finding one Person");
		
		Person person =new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Lucas");
		person.setLastName("Fernandes Dias");
		person.setAddress("Baureri - São Paulo- Brasil ");
		person.setGender("Male");
		
		
		return person;
	}
	
	private Person mockPerson(int i) {
		
		
		Person person =new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Some Addres in Brasil " + i);
		person.setGender("Male");
		return person;
	}
	
}
