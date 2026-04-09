package com.resources.library.services;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import com.resources.library.models.Book;
import com.resources.library.repositories.BookRepository;

/**
 * Service that contains the business logic for the book catalog.
 */
public class BookService {
    private BookRepository bookRepository;
    private List<Book> books;

    /**
     * Creates the service and loads the catalog from the local repository.
     */
    public BookService(){
        bookRepository = new BookRepository();
        books = bookRepository.loadBooks();
    }

    /**
     * Gets the full catalog in memory.
     * @return List<Book> -> Current list of books
     */
    public List<Book> getAllBooks() {
        return books;
    }

    /**
     * Adds a new book to the catalog after validating its data.
     * @param isbn -> ISBN or unique identifier of the book
     * @param title -> Book title
     * @param author -> Book author
     * @param yearPublished -> Publication year of the book
     * @param genre -> Book genre
     * @param available -> Availability status of the book
     * @throws IllegalArgumentException -> Thrown if the book already exists or the data is invalid
     */
    public void addBook(String isbn, String title, String author, String yearPublished, String genre, Boolean available) {

        if (findByIsbn(isbn) != null){
            throw new IllegalArgumentException("Book with this ISBN already exists");
        }

        Book bookToAdd = new Book(isbn, title, author, yearPublished, genre, available);
        validateBook(bookToAdd);

        books.add(bookToAdd);
        bookRepository.saveBooks(books);

    }

    /**
     * Updates an existing book with the new received data.
     * @param updatedBook -> Book with updated information
     * @throws IllegalArgumentException -> Thrown if the book does not exist or the data is invalid
     */
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

    /**
     * Validates that a book meets the system business rules.
     * @param book -> Book to validate
     * @throws IllegalArgumentException -> Thrown if any value does not meet the rules
     */
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


    /**
     * Deletes a book from the catalog if the ISBN matches the confirmed value.
     * @param book -> Book selected for deletion
     * @param isbnToDelete -> ISBN retyped by the user to confirm the action
     * @throws IllegalArgumentException -> Thrown if the ISBN does not match or the data is invalid
     */
    public void deleteBook(Book book, String isbnToDelete){
        validateBook(book);

        if(book.getIsbn().equals(isbnToDelete)){
            books.remove(book);
            bookRepository.saveBooks(books);
            return;
        }
        throw new IllegalArgumentException("ISBN don't match");
    }

    /**
     * Prepares a book's information to be shown in the detail view.
     * @param bookDetails -> Book whose information will be displayed
     * @throws IllegalArgumentException -> Thrown if the book does not exist or the data is invalid
     */
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

    /**
     * Searches for a book by its ISBN in the in-memory catalog.
     * @param isbn -> ISBN of the book to search
     * @return Book -> Found book or null if it does not exist
     */
    public Book findByIsbn(String isbn){
        for (Book book: books){
            if (isbn.equals(book.getIsbn())){
                return book;
            }
        }
        return null;
    }

    /**
     * Exports the current catalog to a CSV file.
     * @param file -> Destination file for the report
     */
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
