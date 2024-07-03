package services;

import models.Author;
import models.Book;
import models.BorrowDetails;
import models.Borrower;
import repos.*;

import java.util.List;

public class LibraryServiceImpl implements Library{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BorrowerRepository borrowerRepository;
    private final BorrowDetailsRepository borrowDetailsRepository;

    private static Library library;

    private LibraryServiceImpl() {
        this.bookRepository = BookRepositoryImpl.getInstance();
        this.authorRepository = AuthorRepositoryImpl.getInstance();
        this.borrowerRepository = BorrowerRepositoryImpl.getInstance();
        this.borrowDetailsRepository = BorrowDetailsRepositoryImpl.getInstance();
    }

    public static Library getInstance(){
        if(library == null)
            library = new LibraryServiceImpl();
        return library;
    }


    @Override
    public void addBook(Book book) {
        bookRepository.add(book);
    }

    @Override
    public void removeBook(int id) {
        bookRepository.remove(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.get();
    }

    private List<Book> searchBook(String title) {
        return bookRepository.get(title);
    }

    private List<Book> searchBookByAuthor(String author) {
        return bookRepository.getByAuthor(author);
    }

    private List<Book> searchBookByGenre(String genre) {
        return bookRepository.getByGenre(genre);
    }

    @Override
    public List<Book> searchBook(String key, int choice) {
        return switch (choice) {
            case 1 -> searchBook(key);
            case 2 -> searchBookByAuthor(key);
            case 3 -> searchBookByGenre(key);
            default -> null;
        };
    }


    private List<Book> sortBooks() {
        return bookRepository.sort();
    }

    private List<Book> sortBooksByAuthor() {
        return bookRepository.sortByAuthor();
    }

    private List<Book> sortBooksByPublicationDate() {
        return bookRepository.sortByDate();
    }

    @Override
    public List<Book> sortBooks(int choice) {
        return switch (choice){
            case 1 -> sortBooks();
            case 2 -> sortBooksByAuthor();
            case 3 -> sortBooksByPublicationDate();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }



    /**Author Services**/
    @Override
    public void addAuthor(Author author) {
        authorRepository.add(author);
    }

    @Override
    public void removeAuthor(int id) {
        authorRepository.remove(id);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.get();
    }

    @Override
    public List<Author> searchAuthors(String name) {
        return authorRepository.get(name);
    }

    private List<Author> sortAuthors() {
        return authorRepository.sort();
    }

    private List<Author> sortByBooksWritten() {
        return authorRepository.sortByBooksWritten();
    }

    @Override
    public List<Author> sortAuthors(int choice) {
        return switch (choice){
            case 1 -> sortAuthors();
            case 2 -> sortByBooksWritten();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }

    /**Borrower Services**/
    @Override
    public void addBorrower(Borrower borrower) {
        borrowerRepository.add(borrower);
    }

    @Override
    public void removeBorrower(String username) {
        borrowerRepository.remove(username);
    }

    @Override
    public List<Borrower> getBorrowers() {
        return borrowerRepository.get();
    }

    @Override
    public List<Borrower> searchBorrowers(String name) {
        return borrowerRepository.search(name);
    }

    @Override
    public List<Borrower> sortBorrowers() {
        return borrowerRepository.sort();
    }

    /*Library Services*/

    @Override
    public void borrow(BorrowDetails borrowDetails) {
        borrowDetailsRepository.add(borrowDetails);
    }

    @Override
    public List<BorrowDetails> track() {
        return borrowDetailsRepository.get();
    }

    @Override
    public void returnBook(int id) throws IllegalAccessException {
        borrowDetailsRepository.returnBook(id);
    }

    @Override
    public List<BorrowDetails> unReturned() {
        return borrowDetailsRepository.unReturned();
    }
}
