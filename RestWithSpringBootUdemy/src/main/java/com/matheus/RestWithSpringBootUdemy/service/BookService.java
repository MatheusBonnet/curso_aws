package com.matheus.RestWithSpringBootUdemy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheus.RestWithSpringBootUdemy.converter.DozerConverter;
import com.matheus.RestWithSpringBootUdemy.data.vo.BookVO;
import com.matheus.RestWithSpringBootUdemy.exception.ResourceNotFoundException;
import com.matheus.RestWithSpringBootUdemy.model.Book;
import com.matheus.RestWithSpringBootUdemy.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repository;
		
	public BookVO create(BookVO book) {
		Book entity = DozerConverter.parseObject(book, Book.class);
		BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}	
	
	public BookVO findById(Long id) {

		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}
		
	public BookVO update(BookVO book) {
		Book entity = repository.findById(book.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
