package repos;

import models.Author;

import java.util.*;

public class AuthorRepositoryImpl implements AuthorRepository{
    private final Map<Integer, Author> authors;
    private final BookRepository bookRepository;
    private static AuthorRepository authorRepository;

    private AuthorRepositoryImpl() {
        this.authors = new HashMap<>();
        this.bookRepository = BookRepositoryImpl.getInstance();
    }

    static public AuthorRepository getInstance(){
        if(authorRepository == null){
            authorRepository = new AuthorRepositoryImpl();
        }
        return authorRepository;
    }


    @Override
    public void add(Author author) {
        authors.put(author.getId(),author);
    }

    @Override
    public void remove(int id) {
        if(this.authors.containsKey(id))
            authors.remove(id);
        else
            throw new NullPointerException("No Books With this Id: "+id+" Found");
    }

    @Override
    public List<Author> get() {
        return this.authors
                .values()
                .stream()
                .toList();
    }

    @Override
    public List<Author> get(String name) {
        return this.authors.values()
                .stream()
                .filter(author -> author.getName().toLowerCase().contains(name))
                .toList();
    }

    @Override
    public Author get(int id) {
        Optional<Author> author = Optional.ofNullable(authors.get(id));
        return author.orElseThrow(()->new NullPointerException("No Author with this Id"));
    }

    @Override
    public List<Author> sort() {
        List<Author> authors = new ArrayList<>(this.authors.values()
                .stream()
                .toList());
        authors.sort(Comparator.comparing(Author::getName));
        return authors;
    }

    @Override
    public List<Author> sortByBooksWritten() {
        List<Author> authors = new ArrayList<>(this.authors.values()
                .stream()
                .toList());
        authors.sort((a,b) -> bookRepository.bookCount(a) - bookRepository.bookCount(b));
        return authors;
    }
}
