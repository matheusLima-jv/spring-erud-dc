package br.nbb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.nbb.dataVO.v1.PersonVO;
import br.nbb.dataVO.v2.PersonVOV2;
import br.nbb.services.PersonServices;
import br.nbb.util.MediaType;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	@GetMapping(produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML })
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}",
			produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML })
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping(
			consumes = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML },
			produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML })
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}
	
	@PostMapping(value = "/v2", 
			consumes = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML },
			produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML })
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
		return service.createV2(person);
	}
	
	@PutMapping(
			consumes = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML },
			produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML})
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
