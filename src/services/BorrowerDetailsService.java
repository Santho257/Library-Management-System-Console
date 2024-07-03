package services;

import models.Book;
import models.BorrowDetails;
import repos.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BorrowerDetailsService {
    private static final Scanner in = SingletonScanner.getInstance();
    private final BorrowDetailsRepository borrowDetailsRepository;
    private final AuthenticationService authenticationService;
    private final BookRepository bookRepository;
    private static BorrowerDetailsService borrowerDetailsService;

    private BorrowerDetailsService(){
        this.borrowDetailsRepository = BorrowDetailsRepositoryImpl.getInstance();
        this.authenticationService = AuthenticationService.getInstance();
        this.bookRepository = BookRepositoryImpl.getInstance();
    }
    public static BorrowerDetailsService getInstance() {
        if(borrowerDetailsService == null)  borrowerDetailsService = new BorrowerDetailsService();
        return borrowerDetailsService;
    }

    public BorrowDetails create(){
        Book book;
        while(true){
            System.out.println("Choose Book: \n"+bookRepository.get());
            System.out.println("Enter -1 to cancel");
            int bookId;
            try{
                bookId = in.nextInt();in.nextLine();
                if(bookId == -1)    throw new IllegalStateException("");
                book = bookRepository.get(bookId);
                break;
            }
            catch (InputMismatchException ex){
                System.out.println("Enter Number from the list");
                in.nextLine();
            }
            catch (NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        }
        return new BorrowDetails(authenticationService.getBorrower(),book);
    }

    public int returnId() {
        int id;
        try {
            System.out.println("Enter -1 to exit");
            System.out.println("Choose the track Id to get Removed: \n" + borrowDetailsRepository.get(authenticationService.getBorrower()));
            id = in.nextInt();
            if(id == -1)    throw new NullPointerException("");
        }
        catch (InputMismatchException ex){
            System.out.println("Enter Valid Number");
            return returnId();
        }
        return id;
    }
}
