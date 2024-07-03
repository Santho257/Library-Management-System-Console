package repos;

import models.Author;
import models.Book;

import java.util.*;

public class BookRepositoryImpl implements BookRepository {
    private Map<Integer, Book> books;
    private static BookRepository bookRepository;

    private BookRepositoryImpl() {
        this.books = new HashMap<>();
    }
    public static BookRepository getInstance(){
        if(bookRepository == null){
            bookRepository = new BookRepositoryImpl();
        }
        return bookRepository;
    }


    @Override
    public void add(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public void remove(int bookId) {
        if(this.books.containsKey(bookId))
            this.books.remove(bookId);
        else
            throw new NullPointerException("No Books With this Id: "+bookId+" Found");
    }

    @Override
    public List<Book> get() {
        return this.books.values().stream().toList();
    }

    @Override
    public List<Book> get(String title) {
        return this.books.values().stream()
                .filter(book -> book.getTitle()
                        .toLowerCase()
                        .contains(title.toLowerCase()))
                .toList();
    }

    @Override
    public Book get(int id) {
        Optional<Book> book = Optional.ofNullable(books.get(id));
        return book.orElseThrow(()->new NullPointerException("No Book with this Id"));
    }

    @Override
    public List<Book> getByGenre(String genre) {
        return this.books.values().stream()
                .filter(book -> book.getGenre()
                        .toString()
                        .toLowerCase()
                        .contains(genre.toLowerCase()))
                .toList();
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return this.books.values().stream()
                .filter(book -> book
                        .getAuthor()
                        .getName()
                        .toLowerCase()
                        .contains(author.toLowerCase()))
                .toList();
    }

    @Override
    public List<Book> sort() {
        List<Book> books = new ArrayList<>(this.books.values().stream().toList());
        books.sort(Comparator.comparing(Book::getTitle));
        return books;
    }

    @Override
    public List<Book> sortByDate() {
        List<Book> books = new ArrayList<>(this.books.values().stream().toList());
        books.sort(Comparator.comparing(Book::getPublicationDate));
        return books;
    }

    @Override
    public List<Book> sortByAuthor() {
        List<Book> books = new ArrayList<>(this.books.values().stream().toList());
        books.sort(Comparator.comparing(a -> a.getAuthor().getName()));
        return books;
    }

    public int bookCount(Author author) {
        return (int) this.books.values().stream()
                .filter(book -> book.getAuthor().equals(author))
                .count();
    }
}
