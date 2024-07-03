package repos;

import models.Author;
import models.Book;

import java.util.List;

public interface BookRepository {
    void add(Book book);
    void remove(int bookId);
    List<Book> get();
    List<Book> get(String title);
    Book get(int id);
    List<Book> getByGenre(String genre);
    List<Book> getByAuthor(String author);
    List<Book> sort();
    List<Book> sortByDate();
    List<Book> sortByAuthor();
    int bookCount(Author author);
}
