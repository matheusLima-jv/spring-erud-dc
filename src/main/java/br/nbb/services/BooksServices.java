package br.nbb.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.nbb.Controllers.PersonController;
import br.nbb.dataVO.v1.BooksVO;
import br.nbb.exception.MathOpExcep;
import br.nbb.mapper.DozerMapper;
import br.nbb.model.Books;
import br.nbb.repositories.BooksRepository;

@Service
public class BooksServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	BooksRepository booksRepo;
	
	public List<BooksVO> findAllBooks(){
		
		logger.info("Finding all books!");
		
		var books = DozerMapper.parseListObject(booksRepo.findAll(), BooksVO.class);
		books.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
		return books;
	}
	
	public BooksVO findbyIdBook(Long id) {
		
		logger.info("Finding books!");
		
		var books = booksRepo.findById(id).orElseThrow(() -> new MathOpExcep("No records found for this ID!"));
		
		var vo = DozerMapper.parseObject(books, BooksVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public BooksVO create(BooksVO bks) {

		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(bks, Books.class);
		var vo = DozerMapper.parseObject(booksRepo.save(entity), BooksVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());
		return vo;
		
	}
	
	public BooksVO updateBooks(BooksVO bks) {
		
		logger.info("Updating one books!");
		
		var entity = booksRepo.findById(bks.getId()).
				orElseThrow(() -> new MathOpExcep("No records found for this ID!"));
		entity.setAuthor(bks.getAuthor());
	    entity.setLaunch_date(bks.getLaunch_date());
	    entity.setPrice(bks.getPrice());
	    entity.setTitle(bks.getTitle());

	    var vo = DozerMapper.parseObject(booksRepo.save(entity), BooksVO.class);
	    vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());
	    return vo;
	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one Book!");
		
		var entity = booksRepo.findById(id)
				.orElseThrow(() -> new MathOpExcep("No records found for this ID!"));
		booksRepo.delete(entity);
	}
	
}
