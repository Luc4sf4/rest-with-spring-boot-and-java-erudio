package br.com.erudio.unittests.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.erudio.data.vo.v1.BookVo;

import br.com.erudio.model.Book;


public class MockBook {


	public Book mockEntity() {
        return mockEntity(0);
    }
	   public BookVo mockVo() {
	        return mockVO(0);
	    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVo> mockVoList() {
        List<BookVo> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Some Author Test" + number);
        book.setTitle("Some Title Test" + number);
        book.setPrice(25D);
        book.setId(number.longValue());
        book.setLaunchDate(new Date());
        return book;
    }

    public BookVo mockVO(Integer number) {
        BookVo book = new BookVo();
        book.setKey(number.longValue());
        book.setAuthor("Some Author Test" + number);
        book.setTitle("Some Title Test" + number);
        book.setPrice(25D);
        
        book.setLaunchDate(new Date());
        return book;
    }

}
