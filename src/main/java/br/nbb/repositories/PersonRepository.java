package br.nbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.nbb.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}

