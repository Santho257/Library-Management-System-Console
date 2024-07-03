package services;

import helper.ArrayToStringHelper;
import models.Borrower;
import repos.BorrowerRepository;
import repos.BorrowerRepositoryImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthenticationService {
    private final BorrowerRepository borrowerRepository;
    private final BorrowerService borrowerService;
    private final Library library;
    private final Scanner in;
    private static AuthenticationService authenticationService;

    private Borrower borrower;
    private boolean admin;

    private AuthenticationService(){
        borrowerRepository = BorrowerRepositoryImpl.getInstance();
        borrowerService = BorrowerService.getInstance();
        library = LibraryServiceImpl.getInstance();
        in = SingletonScanner.getInstance();
    }
    public static AuthenticationService getInstance(){
        if(authenticationService == null)   authenticationService = new AuthenticationService();
        return authenticationService;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    private boolean login(){
        String username;
        while(true) {
            System.out.println("Enter 'cancel' to cancel");
            System.out.print("Enter Username: ");
            username = in.nextLine().toLowerCase().trim();
            if(username.equals("cancel"))
                return false;
            if (!borrowerRepository.isExists(username))
                System.out.println("Username Not Found");
            else break;
        }
        Borrower borrower = borrowerRepository.get(username);
        System.out.print("Enter Password: ");
        String password = in.nextLine();
        if(!ArrayToStringHelper.arrToString(borrower.getPassword()).equals(password)) {
            System.out.println("Incorret passowrd");
            return false;
        }
        this.borrower = borrower;
        return true;
    }

    public void logout(){
        if(admin) admin = false;
        if(borrower != null)    borrower = null;
    }

    private boolean signUp(){
        try {
            Borrower borrower = borrowerService.create();
            library.addBorrower(borrower);
            this.borrower = borrower;
        }catch (NullPointerException ex){
            return false;
        }
        return true;
    }

    private boolean adminLogin(){
        System.out.print("Enter Username: ");
        String username = in.nextLine();
        System.out.print("Enter Password: ");
        String password = in.nextLine();
        if(username.equals("admin") && password.equals("admin")){
            admin = true;
        }
        else    System.out.println("Invalid Credentials!!!");
        return admin;
    }

    public int authenticationMenu(){
        System.out.println("Enter:\n1. Login\n2. Sign up\n3. Admin Login\n4. Exit");
        int choice;
        try {
            choice = in.nextInt();in.nextLine();
        }
        catch (InputMismatchException ex){
            System.out.println("Enter Valid Input: ");
            in.nextLine();
            return authenticationMenu();
        }
        switch (choice){
            case 1:
                if(!login())    authenticationMenu();
                break;
            case 2:
                if(!signUp())   authenticationMenu();
                break;
            case 3:
                if(!adminLogin())   authenticationMenu();
                break;
            case 4:
                return -1;
            default:
                System.out.println("Enter Valid Input!!");
                return authenticationMenu();
        }
        return 1;
    }



}
