package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVo;
import br.com.erudio.data.vo.v2.PersonVoV2;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositorys.PersonRepository;

@Service // Using to Spring Boot see this class wil be inject in runtime in another class
			// in this application
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	private PersonRepository repository;
	@Autowired
	private PersonMapper mapper;

	public PersonVo create(PersonVo person) {

		logger.info("Creating one person! ");

		var entity = DozerMapper.parseObject(person, Person.class) ;
		
		var vo = DozerMapper.parseObject (repository.save(entity), PersonVo.class);
		return vo;

	}
	public PersonVoV2 createV2(PersonVoV2 person) {
		
		logger.info("Creating one person with V2! ");
		
		var entity = mapper.converVoToEntity(person) ;
		
		var vo = mapper.converEntityToVo( repository.save(entity));
		return vo;
		
	}

	public PersonVo update(PersonVo person) {

		logger.info("Updating one person! ");

		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject (repository.save(entity), PersonVo.class);
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

		return DozerMapper.parseListObjects(repository.findAll(), PersonVo.class) ;

	}

	public PersonVo findByID(Long id) {

		logger.info("Finding one PersonVo");

		var entity= repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));
		
		return DozerMapper.parseObject(entity, PersonVo.class);
		
	}

}
