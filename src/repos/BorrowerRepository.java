package repos;

import models.Borrower;

import java.util.List;

public interface BorrowerRepository {
    void add(Borrower borrower);
    void remove(String username);
    List<Borrower> get();
    List<Borrower> search(String name);
    Borrower get(String username);

    boolean isExists(String username);

    List<Borrower> sort();
}
