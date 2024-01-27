package br.nbb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.nbb.model.Books;

@Repository

public interface BooksRepository extends JpaRepository<Books, Long>  {

}
