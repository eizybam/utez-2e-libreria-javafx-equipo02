package com.resources.library.services;

import com.resources.library.models.Book;
import com.resources.library.repositories.BookRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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

    private void validateBook(Book book) {

        if (book.getIsbn().length() != 10) {
            throw new IllegalArgumentException("ISBN must be 10 characters long");
        }

        if (book.getTitle() == null || book.getTitle().isBlank() || book.getTitle().length() < 3) {
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

        if (book.getGenre() == null){
            throw new IllegalArgumentException("Genre must be selected.");
        }

    }


    public void deleteBook(Book book, String isbnToDelete){
        validateBook(book);

        if(book.getIsbn().equals(isbnToDelete)){
            books.remove(book);
            bookRepository.saveBooks(books);
            return;
        }
        throw new IllegalArgumentException("ISBN don't match");
    }

    public void viewBookDetails(Book bookDetails){
        validateBook(bookDetails);

        Book viewDetails = findByIsbn(bookDetails.getIsbn());

        if(bookDetails == null){
            throw new IllegalArgumentException("Book with this ISBN doesn't exists");
        }
        viewDetails.setAuthor(bookDetails.getAuthor());
        viewDetails.setIsbn(bookDetails.getIsbn());
        viewDetails.setGenre(bookDetails.getGenre());
        viewDetails.setTitle(bookDetails.getTitle());
        viewDetails.setYearPublished(bookDetails.getYearPublished());
        viewDetails.setAvailable(bookDetails.isAvailable());

    }

    public Book findByIsbn(String isbn){
        for (Book book: books){
            if (isbn.equals(book.getIsbn())){
                return book;
            }
        }
        return null;
    }

    public void exportReport(File file){
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))){
            writer.println("ISBN, Title, Author, Year Published, Genre, Available");

            for (Book book : books){
                String available = book.isAvailable() ? "Yes" : "No";

                writer.println(
                        book.getIsbn() + "," +
                        book.getTitle() + "," +
                        book.getAuthor() + "," +
                        book.getYearPublished() + "," +
                        book.getGenre() + "," +
                        available);
            }

        } catch (Exception e){
            throw new RuntimeException("Error trying to export: " + e.getMessage());
        }
    }

}
