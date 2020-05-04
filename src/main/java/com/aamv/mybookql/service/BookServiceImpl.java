package com.aamv.mybookql.service;

import com.aamv.mybookql.model.Book;
import com.aamv.mybookql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public Book createBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        book.setAuthors(authorService.createAuthors(book.getAuthors()));
        return bookRepository.save(book).block();
    }

    @Override
    public Book updateBook(String bookId, Book book) {
        Book persistedBook = getBook(bookId);
        persistedBook.setTitle(book.getTitle());
        persistedBook.setSubtitle(book.getSubtitle());
        persistedBook.setSynopsis(book.getSynopsis());
        persistedBook.setAuthors(book.getAuthors());
        persistedBook.setPublicationDate(book.getPublicationDate());
        persistedBook.setCategories(book.getCategories());
        persistedBook.setIsbn10(book.getIsbn10());
        persistedBook.setIsbn13(book.getIsbn13());
        persistedBook.setIdGoogle(book.getIdGoogle());
        return bookRepository.save(persistedBook).block();
    }

    @Override
    public Book getBook(String bookId) {
        return bookRepository.findById(bookId).block();
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll().collectList().block();
    }

    @Override
    public void deleteBook(String bookId) {
        bookRepository.deleteById(bookId);
    }
}
