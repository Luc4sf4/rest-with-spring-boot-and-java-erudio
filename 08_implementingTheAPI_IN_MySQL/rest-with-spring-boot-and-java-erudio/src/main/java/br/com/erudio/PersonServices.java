package br.com.erudio;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositorys.PersonRepository;

@Service //Using to Spring Boot see this class wil be inject in runtime in another class in this application
public class PersonServices {

	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	
	
	public Person create(Person person) {
		
		logger.info("Creating one person! ");
		
		return repository.save(person);
		
	}
	
public Person update(Person person) {
		
		logger.info("Updating one person! ");
		
		
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));
		
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		
		return repository.save(person);
		
	}

public void delete(Long id) {
	
	logger.info("deleting one person! ");
	
	Person entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));
	
		repository.delete(entity);
}
	
	
	public List<Person> findAll() {
		
		logger.info("Find all people! ");

		return repository.findAll();
		
	}

	public Person findByID(Long id) {
		
		logger.info("Finding one Person");
		
		
		
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));
	}
	
}
