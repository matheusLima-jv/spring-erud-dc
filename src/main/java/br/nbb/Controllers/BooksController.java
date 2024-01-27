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

import br.nbb.dataVO.v1.BooksVO;
import br.nbb.services.BooksServices;
import br.nbb.util.MediaType;

@RestController
@RequestMapping("api/books/v1")
public class BooksController {
	
	@Autowired
	BooksServices booksServ;
	
	@GetMapping(produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML })
	public List<BooksVO> findAll() {
		return booksServ.findAllBooks();
	}
	
	@GetMapping(value = "/{id}",
			produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML })
	public BooksVO findById(@PathVariable(value = "id") Long id) {
		return booksServ.findbyIdBook(id);
	}
	
	@PostMapping(
			consumes = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML },
			produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML })
	public BooksVO create(@RequestBody BooksVO bks) {
		return booksServ.create(bks);
	}
	
	@PutMapping(
			consumes = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML },
			produces = { MediaType.APP_JSON, MediaType.APP_XML,MediaType.APP_YML})
	public BooksVO update(@RequestBody BooksVO bks) {
		return booksServ.updateBooks(bks);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		booksServ.delete(id);
		return ResponseEntity.noContent().build();
	}

}
