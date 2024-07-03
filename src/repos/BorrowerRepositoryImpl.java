package repos;

import models.Borrower;

import java.util.*;

public class BorrowerRepositoryImpl implements BorrowerRepository{
    private Map<String , Borrower> borrowers;
    private static BorrowerRepository borrowerRepository;
    private BorrowerRepositoryImpl(){
        this.borrowers = new HashMap<>();
    }

    public static BorrowerRepository getInstance() {
        if(borrowerRepository == null){
            borrowerRepository = new BorrowerRepositoryImpl();
        }
        return borrowerRepository;
    }

    @Override
    public void add(Borrower borrower) {
        borrowers.put(borrower.getUsername(),borrower);
    }

    @Override
    public void remove(String username) {
        if(borrowers.containsKey(username))   borrowers.remove(username);
        else    throw new NullPointerException("No Borrower Find With the ID: "+username);
    }

    @Override
    public List<Borrower> get() {
        return this.borrowers.values().stream().toList();
    }

    @Override
    public List<Borrower> search(String name) {
        return this.borrowers.values().stream()
                .filter(borrower -> borrower.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public Borrower get(String username) {
        if(borrowers.containsKey(username))
            return borrowers.get(username);
        else throw new NullPointerException("No Borrower with that Id Found");
    }

    @Override
    public boolean isExists(String username){
        return borrowers.containsKey(username);
    }

    @Override
    public List<Borrower> sort() {
        List<Borrower>  borrowers1 = new ArrayList<>(this.borrowers.values().stream().toList());
        borrowers1.sort(Comparator.comparing(Borrower::getName));
        return borrowers1;
    }
}
