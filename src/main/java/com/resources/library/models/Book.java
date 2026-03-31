package com.resources.library.models;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String yearPublished;
    private String genre;
    private boolean available;

    public Book(String isbn, String title, String author, String yearPublished, String genre, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.available = available;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
