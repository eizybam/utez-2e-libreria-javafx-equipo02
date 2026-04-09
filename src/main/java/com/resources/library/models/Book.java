package com.resources.library.models;

/**
 * Model that represents a book in the library catalog.
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String yearPublished;
    private String genre;
    private boolean available;

    /**
     * Creates a new book with its main data.
     * @param isbn -> ISBN or unique identifier of the book
     * @param title -> Book title
     * @param author -> Book author
     * @param yearPublished -> Publication year of the book
     * @param genre -> Book genre
     * @param available -> Availability status of the book
     */
    public Book(String isbn, String title, String author, String yearPublished, String genre, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.available = available;
    }

    /**
     * Gets the book ISBN.
     * @return String -> Book ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Updates the book ISBN.
     * @param isbn -> New book ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the book title.
     * @return String -> Book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Updates the book title.
     * @param title -> New book title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the book author.
     * @return String -> Book author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Updates the book author.
     * @param author -> New book author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publication year.
     * @return String -> Book publication year
     */
    public String getYearPublished() {
        return yearPublished;
    }

    /**
     * Updates the publication year.
     * @param yearPublished -> New publication year
     */
    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    /**
     * Gets the book genre.
     * @return String -> Book genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Updates the book genre.
     * @param genre -> New book genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Indicates whether the book is available.
     * @return boolean -> true if the book is available, false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Updates the availability status of the book.
     * @param available -> New availability status
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
}