package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.erudio.controller.PersonController;
import br.com.erudio.data.vo.v1.PersonVo;
import br.com.erudio.exception.RequeiredObjectIsNullException;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositorys.PersonRepository;

@Service // Using to Spring Boot see this class wil be inject in runtime in another class
			// in this application
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	private PersonRepository repository;

	public PersonVo create(PersonVo person) {

		if(person == null) throw new RequeiredObjectIsNullException();
		
		logger.info("Creating one person! ");

		var entity = DozerMapper.parseObject(person, Person.class) ;
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVo.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;

	}

	public PersonVo update(PersonVo person) {

		if(person == null) throw new RequeiredObjectIsNullException();
		
		logger.info("Updating one person! ");

		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(repository.save(entity), PersonVo.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {

		logger.info("deleting one person! ");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));

		repository.delete(entity);
	}

	public List<PersonVo> findAll() {

		logger.info("Find all people! ");

		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVo.class) ;
		
		persons
		.stream()
		.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		
		return persons;
		
	}

	public PersonVo findByID(Long id) {

		logger.info("Finding one PersonVo");

		var entity= repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));
		
		var vo = DozerMapper.parseObject(entity, PersonVo.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
		
	}

}
