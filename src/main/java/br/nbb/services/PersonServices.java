package br.nbb.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.nbb.PersonController;
import br.nbb.dataVO.v1.PersonVO;
import br.nbb.dataVO.v2.PersonVOV2;
import br.nbb.exception.MathOpExcep;
import br.nbb.mapper.DozerMapper;
import br.nbb.mapper.custom.PersonMapper;
import br.nbb.model.Person;
import br.nbb.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper personMapper;

	public List<PersonVO> findAll() {

		logger.info("Finding all people!");

		var persons =  DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {
		
		logger.info("Finding one person!");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new MathOpExcep("No records found for this ID!"));
		
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) {

		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
		
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {

		logger.info("Creating one person! v2 version ****");
		
		var entity = personMapper.converteEmPer(person);
		var vo = personMapper.converteEmVO(repository.save(entity));
		return vo;
		
	}
	
	public PersonVO update(PersonVO person) {
	    logger.info("Updating one person!");

	    if (person == null || person.getKey() == null) {
	        throw new IllegalArgumentException("Person or Person's key cannot be null!");
	    }

	    var entity = repository.findById(person.getKey())
	            .orElseThrow(() -> new MathOpExcep("No records found for this ID!"));

	    entity.setFirstName(person.getFirstName());
	    entity.setLastName(person.getLastName());
	    entity.setAddress(person.getAddress());
	    entity.setGender(person.getGender());

	    var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	    vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
	    return vo;
	}
	
	public void delete(Long key) {
		
		logger.info("Deleting one person!");
		
		var entity = repository.findById(key)
				.orElseThrow(() -> new MathOpExcep("No records found for this ID!"));
		repository.delete(entity);
	}
}