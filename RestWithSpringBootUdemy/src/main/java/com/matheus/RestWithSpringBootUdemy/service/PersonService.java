package com.matheus.RestWithSpringBootUdemy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.matheus.RestWithSpringBootUdemy.converter.DozerConverter;
import com.matheus.RestWithSpringBootUdemy.data.vo.PersonVO;
import com.matheus.RestWithSpringBootUdemy.model.Person;
import com.matheus.RestWithSpringBootUdemy.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
		
	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public Page<PersonVO> findAll(Pageable p) {
		repository.findAll();
		Page<Person> person = repository.findAll(p);
		return person.map(x -> new PersonVO(x));
	}	
	
	public Page<PersonVO> findPersonByName(String name,Pageable p) {
		repository.findAll();
		Page<Person> person = repository.findPersonByName(name, p);
		return person.map(x -> new PersonVO(x));
	}
	
	public PersonVO findById(Long id) {

		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
		
	public PersonVO update(PersonVO person) {
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePersons(id);			
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}