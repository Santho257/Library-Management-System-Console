package services;

import models.Author;
import models.Book;
import models.BorrowDetails;
import models.Borrower;

import java.util.List;

public interface Library {
    /**Book Services**/
    void addBook(Book book);
    void removeBook(int id);
    List<Book> getBooks();
    List<Book> searchBook(String key,int choice);
    List<Book> sortBooks(int choice);

    /**Author Services**/
    void addAuthor(Author author);
    void removeAuthor(int id);
    List<Author> getAuthors();
    List<Author> searchAuthors(String name);
    List<Author> sortAuthors(int choice);

    /**Borrower Services**/
    void addBorrower(Borrower borrower);
    void removeBorrower(String username);
    List<Borrower> getBorrowers();
    List<Borrower> searchBorrowers(String name);
    List<Borrower> sortBorrowers();

    /**Library Services**/
    void borrow(BorrowDetails borrowDetails);
    List<BorrowDetails> track();
    void returnBook(int id) throws IllegalAccessException;
    List<BorrowDetails> unReturned();
}
