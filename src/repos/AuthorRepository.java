package repos;

import models.Author;

import java.util.List;

public interface AuthorRepository {
    void add(Author author);
    void remove(int id);
    List<Author> get();
    List<Author> get(String name);
    Author get(int id);
    List<Author> sort();
    List<Author> sortByBooksWritten();

}
