package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.erudio.controller.BookController;
import br.com.erudio.data.vo.v1.BookVo;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.repositorys.BookRepository;


@Service
public class BookService {

	private Logger logger = Logger.getLogger(BookService.class.getName());
	
	@Autowired
	private BookRepository repository;
	
	public BookVo create (BookVo book){
		
		logger.info("Adding a new Book");
		
		var entity = DozerMapper.parseObject(book, Book.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVo.class);
		
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	
	public BookVo update(BookVo book){
		
		logger.info("Updating one Book");
		
		
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Not found by this id"));;
		
		
		entity.setAuthor(book.getAuthor());		
		entity.setLaunchDate(book.getLaunchDate());		
		entity.setPrice(book.getPrice()); 
		entity.setTitle(book.getTitle());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVo.class);	
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
		
				
	}
	
	
	
	
	public BookVo findByID(Long id){
		
		var entity = repository.findById(id).
				orElseThrow( () -> new ResourceNotFoundException("Not found by this Id"));
		
		var vo = DozerMapper.parseObject(entity, BookVo.class);
		
		return vo;
		
		
	}
	
	public List<BookVo> findAll(){
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVo.class);
		
		books
		.stream()
		.forEach( b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		return books;
		
	}
	
	public void delete(Long id) {

		logger.info("deleting one Book! ");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found for this id"));

		repository.delete(entity);
	}
	
	
	
}
