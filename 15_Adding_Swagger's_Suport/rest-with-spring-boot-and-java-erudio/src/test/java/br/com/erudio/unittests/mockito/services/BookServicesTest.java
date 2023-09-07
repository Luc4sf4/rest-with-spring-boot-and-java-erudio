package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.BookVo;
import br.com.erudio.exception.RequeiredObjectIsNullException;
import br.com.erudio.model.Book;
import br.com.erudio.repositorys.BookRepository;
import br.com.erudio.services.BookService;
import br.com.erudio.unittests.mock.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;

	@InjectMocks
	private BookService service;

	@Mock
	BookRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {

		input = new MockBook();
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void testFindByID() {

		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findByID(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author Test1", result.getAuthor());
		assertEquals("Some Title Test1", result.getTitle());
		assertEquals(25D, result.getPrice());
		assertNotNull(result.getLaunchDate());
		
	}

	@Test
	void testCreate() {
		Book entity = input.mockEntity(1);
		Book persisted = entity;
		persisted.setId(1L);

		BookVo vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var bookFour = service.create(vo);

		assertNotNull(bookFour);
		assertNotNull(bookFour.getKey());
		assertNotNull(bookFour.getLinks());
		assertTrue(bookFour.toString().contentEquals("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author Test1", bookFour.getAuthor());
		assertEquals("Some Title Test1", bookFour.getTitle());
		assertNotNull(bookFour.getLaunchDate());
		assertEquals(25D, bookFour.getPrice());

	}

	@Test
	void testCreateWithNullBook() {

		Exception exception = assertThrows(RequeiredObjectIsNullException.class, () -> {

			service.update(null);

		});
		String expectedMessage = "It's not allowad to persisted a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	


	
	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		Book persisted = entity;
		persisted.setId(1L);

		BookVo vo = input.mockVO	(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contentEquals("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author Test1", result.getAuthor());
		assertEquals("Some Title Test1", result.getTitle());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
	}
	
	@Test
	void testUpdateWithNullBook() {

		Exception exception = assertThrows(RequeiredObjectIsNullException.class, () -> {

			service.create(null);

		});
		String expectedMessage = "It's not allowad to persisted a null object";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var book = service.findAll();

		assertNotNull(book);
		assertEquals(14,book.size());
		
		var bookOne= book.get(1);

		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertTrue(bookOne.toString().contentEquals("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author Test1", bookOne.getAuthor());
		assertEquals("Some Title Test1", bookOne.getTitle());
		assertNotNull(bookOne.getLaunchDate());
		assertEquals(25D, bookOne.getPrice());
		
		
		var bookFour= book.get(4);
		
		assertNotNull(bookFour);
		assertNotNull(bookFour.getKey());
		assertNotNull(bookFour.getLinks());
		assertTrue(bookFour.toString().contentEquals("links: [</api/book/v1/4>;rel=\"self\"]"));
		assertEquals("Some Author Test4", bookFour.getAuthor());
		assertEquals("Some Title Test4", bookFour.getTitle());
		assertNotNull(bookFour.getLaunchDate());
		assertEquals(25D, bookFour.getPrice());
		

		var bookSeven= book.get(7);
		
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());
		assertTrue(bookSeven.toString().contentEquals("links: [</api/book/v1/7>;rel=\"self\"]"));
		assertEquals("Some Author Test7", bookSeven.getAuthor());
		assertEquals("Some Title Test7", bookSeven.getTitle());
		assertNotNull(bookSeven.getLaunchDate());
		assertEquals(25D, bookSeven.getPrice());

		
		
	}

}
