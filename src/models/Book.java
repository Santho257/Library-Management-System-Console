package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Book{
    private static int count = 0;
    private final int id;
    private String title;
    private Author author;
    private Genre genre;
    private int pages;
    private LocalDateTime publicationDate;

    public Book(String title, Author author, Genre genre, int pages) {
        this.id = ++Book.count;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.publicationDate = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    @Override
    public String toString() {
        return "{Id=" + id +
                ", Title='" + title + '\'' +
                ", Author=" + author.getName() +
                ", Genre=" + genre +
                ", Pages=" + pages +
                ", PublicationDate=" + publicationDate.format(DateTimeFormatter.ISO_DATE) +
                "}\n";
    }
}
