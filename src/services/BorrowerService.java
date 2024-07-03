package services;

import models.Borrower;
import repos.BorrowerRepository;
import repos.BorrowerRepositoryImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final Scanner in = SingletonScanner.getInstance();

    private static BorrowerService borrowerService;
    private BorrowerService(){
        this.borrowerRepository = BorrowerRepositoryImpl.getInstance();
    }
    public static BorrowerService getInstance() {
        if(borrowerService == null)   borrowerService = new BorrowerService();
        return borrowerService;
    }

    public Borrower create(){
        String username;
        while(true) {
            System.out.println("Enter cancel to cancel");
            System.out.print("Enter Username: ");
            username = in.nextLine().trim().toLowerCase();
            if(username.equals("cancel"))   throw new NullPointerException("");
            if(!Pattern.matches("^[a-zA-Z0-9]+$",username)){
                System.out.println("Enter Valid username: (Word Only Comtains Letters and Numbers)");
                continue;
            }
            if(!borrowerRepository.isExists(username))  break;
            else System.out.println("Username Already Exists!!");
        }
        String name;
        while(true) {
            System.out.println("Enter cancel to cancel");
            System.out.print("Enter Name: ");
            name = in.nextLine().trim();
            if(name.equalsIgnoreCase("cancel"))   throw new NullPointerException("");
            if (!Pattern.matches("([a-zA-Z.\\s]+)+", name)) {
                System.out.println("Enter Valid Name: (Word Only Comtains Letters, dot  and spaces)");
            }
            else break;
        }
        System.out.print("Enter Password: ");
        String password = in.nextLine().trim();
        return new Borrower(username, name.toUpperCase(),password.toCharArray());
    }

    public String removeId() {
        String username;
        try {
            List<Borrower> borrowers =  borrowerRepository.get();
            if(borrowers.isEmpty()) return "";
            System.out.println("Enter cancel to cancel");
            System.out.println("Choose the Author Id to get Removed: \n" + borrowers);
            username = in.nextLine();
            if(username.equalsIgnoreCase("cancel")) throw new NullPointerException("");
        }
        catch (InputMismatchException ex){
            System.out.println("Enter Valid username");
            return removeId();
        }
        return username;
    }
}
