package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BorrowDetails {
    private static int count = 0;
    private final int id;
    private Book book;
    private Borrower borrower;
    private final LocalDateTime borrowedOn;
    private LocalDateTime returnedOn;

    public BorrowDetails(Borrower borrower, Book book) {
        this.id = ++count;
        this.borrower = borrower;
        this.book = book;
        this.borrowedOn = LocalDateTime.now();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public LocalDateTime getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(LocalDateTime returnedOn) {
        this.returnedOn = returnedOn;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getBorrowedOn() {
        return borrowedOn;
    }

    @Override
    public String toString() {
        return "{Id=" + id +
                ", Book=" + book.getTitle() +
                ", Borrower=" + borrower.getName() +
                ", BorrowedOn=" + borrowedOn.format(DateTimeFormatter.ISO_DATE) +
                ", ReturnedOn=" + ((returnedOn != null)
                ? returnedOn.format(DateTimeFormatter.ISO_DATE)
                : "Not Returned Yet") +
                "}\n";
    }
}
