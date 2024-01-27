package br.nbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.nbb.model.Person;

@Repository

public interface PersonRepository extends JpaRepository<Person, Long> {}

