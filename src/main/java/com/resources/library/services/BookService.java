package com.resources.library.services;

import com.resources.library.models.Book;
import com.resources.library.repositories.BookRepository;

import java.util.List;

public class BookService {
    private BookRepository bookRepository;
    private List<Book> books;

    public BookService(){
        bookRepository = new BookRepository();
        books = bookRepository.loadBooks();
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void addBook(Book book) {
        validateBook();
        if (findByIsbn(book.getIsbn()) != null){
            throw new IllegalArgumentException("A book with this ISBN already exists.");
        }

        books.add(book);
        bookRepository.saveBooks(books);

    }

    public void updateBook(Book updatedBook){
        validateBook();

        Book bookToUpdate = findByIsbn(updatedBook.getIsbn());
        if (bookToUpdate == null){
            throw new IllegalArgumentException("Book with this ISBN doesn't exists");
        }

        bookToUpdate.setAuthor(updatedBook.getAuthor());
        bookToUpdate.setIsbn(updatedBook.getIsbn());
        bookToUpdate.setGenre(updatedBook.getGenre());
        bookToUpdate.setTitle(updatedBook.getTitle());
        bookToUpdate.setYearPublished(updatedBook.getYearPublished());
        bookToUpdate.setAvailable(updatedBook.isAvailable());

        bookRepository.saveBooks(books);

    }

    public void deleteBook(Book book){
        validateBook();

        books.remove(book);
        bookRepository.saveBooks(books);
    }

    public void validateBook(){

    }

    public Book findByIsbn(String isbn){
        for (Book book: books){
            if (isbn.equals(book.getIsbn())){
                return book;
            }
        }
        return null;
    }

}
