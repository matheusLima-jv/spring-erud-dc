package br.nbb.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.nbb.dataVO.v2.PersonVOV2;
import br.nbb.model.Person;

@Service
public class PersonMapper {
	
	public PersonVOV2 converteEmVO(Person person) {
		
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setAddress(person.getAddress());
		vo.setGender(person.getGender());
		vo.setBirthDT(new Date());
		return vo;
	}
	
public Person converteEmPer(PersonVOV2 person) {
		
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		//entity.setBirthDT(new Date()); (aqui seria o ajuste na v2 )
		return entity;
	}

}
