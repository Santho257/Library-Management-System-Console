package repos;

import models.BorrowDetails;
import models.Borrower;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowDetailsRepositoryImpl implements BorrowDetailsRepository{
    private final Map<Integer, BorrowDetails> borrowDetailsMap;
    private final BorrowerRepository borrowerRepository;
    private static BorrowDetailsRepository borrowDetailsRepository;

    private BorrowDetailsRepositoryImpl() {
        this.borrowDetailsMap = new HashMap<>();
        this.borrowerRepository = BorrowerRepositoryImpl.getInstance();
    }
    public static BorrowDetailsRepository getInstance(){
        if(borrowDetailsRepository == null){
            borrowDetailsRepository = new BorrowDetailsRepositoryImpl();
        }
        return borrowDetailsRepository;
    }

    @Override
    public void add(BorrowDetails borrowDetails) {
        borrowDetailsMap.put(borrowDetails.getId(),borrowDetails);
    }

    @Override
    public List<BorrowDetails> get() {
        return this.borrowDetailsMap.values().stream().toList();
    }

    @Override
    public List<BorrowDetails> get(String username) {
        return this.borrowDetailsMap.values()
                .stream()
                .filter(borrowDetails ->
                        borrowDetails.getBorrower().getUsername().equals(username.toLowerCase()))
                .toList();
    }

    @Override
    public void returnBook(int id) throws IllegalAccessException {
        if(borrowDetailsMap.containsKey(id)){
            BorrowDetails borrowDetails = borrowDetailsMap.get(id);
            if(borrowDetails.getReturnedOn() == null)
                borrowDetails.setReturnedOn(LocalDateTime.now());
            else
                throw new IllegalAccessException("The Book has already been returned");
        }
        else{
            throw new NullPointerException("No Details With this Id Found");
        }
    }

    @Override
    public List<BorrowDetails> unReturned() {
        return this.borrowDetailsMap.values()
                .stream()
                .filter(bd -> bd.getReturnedOn() == null)
                .toList();
    }

    @Override
    public List<BorrowDetails> get(Borrower borrower) {
        return this.borrowDetailsMap.values()
                .stream()
                .filter(bd -> bd.getBorrower().getUsername()
                        .equals(borrower.getUsername()))
                .toList();
    }

}
