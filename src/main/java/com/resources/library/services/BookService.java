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

    public void addBook(String isbn, String title, String author, String yearPublished, String genre, Boolean available) {

        if (findByIsbn(isbn) != null){
            throw new IllegalArgumentException("Book with this ISBN already exists");
        }

        Book bookToAdd = new Book(isbn, title, author, yearPublished, genre, available);
        validateBook(bookToAdd);

        books.add(bookToAdd);
        bookRepository.saveBooks(books);

    }

    public void updateBook(Book updatedBook){
        validateBook(updatedBook);

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

    private void validateBook(Book book){

        if (book.getIsbn().length() != 10){
            throw new IllegalArgumentException("ISBN must be 10 characters long");
        }

        if (book.getTitle() == null || book.getTitle().isBlank() || book.getTitle().length() < 3){
            throw new IllegalArgumentException("Title must be at least 3 characters.");
        }

        if (book.getAuthor() == null || book.getAuthor().isBlank() || book.getAuthor().length() < 3) {
            throw new IllegalArgumentException("Author must be at least 3 characters.");
        }

        try {
            int yearPublished = Integer.parseInt(book.getYearPublished());
            if (yearPublished < 1500 || yearPublished > 2026) {
                throw new IllegalArgumentException("Year published must be between 1500 and the current year.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Year published must be a valid 4-digit year.");
        }



    public void deleteBook(Book book){
        validateBook();

        books.remove(book);
        bookRepository.saveBooks(books);
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
