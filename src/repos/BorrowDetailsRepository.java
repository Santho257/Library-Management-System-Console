package repos;

import models.BorrowDetails;
import models.Borrower;

import java.util.List;

public interface BorrowDetailsRepository {
    void add(BorrowDetails borrowDetails);
    List<BorrowDetails> get();
    List<BorrowDetails> get(String username);
    void returnBook(int id) throws IllegalAccessException;
    List<BorrowDetails> unReturned();
    List<BorrowDetails> get(Borrower borrower);
}
