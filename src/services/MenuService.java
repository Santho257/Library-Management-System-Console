package services;


import models.Author;
import models.Book;
import models.BorrowDetails;
import models.Borrower;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuService {
    private final Scanner in;
    private final Library library;
    private final BookService bookService;
    private final AuthorService authorService;
    private final AuthenticationService authenticationService;
    private final BorrowerService borrowerService;
    private final BorrowerDetailsService borrowerDetailsService;

    private static MenuService menuService;

    private MenuService() {
        this.in = SingletonScanner.getInstance();
        this.library = LibraryServiceImpl.getInstance();
        this.bookService = BookService.getInstance();
        this.authorService = AuthorService.getInstance();
        this.authenticationService = AuthenticationService.getInstance();
        this.borrowerService = BorrowerService.getInstance();
        this.borrowerDetailsService = BorrowerDetailsService.getInstance();
    }
    public static MenuService getInstance(){
        if(menuService == null) menuService = new MenuService();
        return menuService;
    }

    private void bookMenu(){
        System.out.println("Enter \n1. Get books\n2. Search\n3. Sort");
        if(authenticationService.isAdmin())
            System.out.println("4. Add Book\n5. Remove Book");
        System.out.println("9. Go Back");
        int choice = 0;
        try{
            choice = in.nextInt();in.nextLine();
        }
        catch (InputMismatchException ex){
            System.out.println("Invalid Input!");
            in.nextLine();
            bookMenu();
        }
        switch (choice){
            case 1:
                List<Book> books = library.getBooks();
                System.out.println(!books.isEmpty()
                        ?books
                        :"Currently No Books Available Come Again Later");
                break;
            case 2:
                int sc = bookService.searchChoice();
                if(sc != 0) {
                    System.out.println("Enter Search key");
                    books = library.searchBook(in.nextLine(), sc);
                    System.out.println(!books.isEmpty()
                            ?books
                            :"No Books Found with the Search Key");
                }
                break;
            case 3:
                int src = bookService.sortChoice();
                if(src != 0) {
                    books = library.sortBooks(src);
                    System.out.println((!books.isEmpty()
                            ? books
                            : "Currently No Books Available Come Again Later"));
                }
                break;
            case 4:
                if(authenticationService.isAdmin()) {
                    try {
                        library.addBook(bookService.createBook());
                        System.out.println("Book Added Successfully");
                    }catch (IllegalStateException | NullPointerException ex){
                        System.out.println(ex.getMessage());
                    }
                }else
                    System.out.println("Invalid Option Choose Again!!");
                break;
            case 5:
                if(authenticationService.isAdmin()) {
                    try {
                        int id = bookService.removeId();
                        if(id == -1){
                            System.out.println("No Books Found");
                            break;
                        }
                        library.removeBook(id);
                        System.out.println("Book Removed Successfully");
                    } catch (NullPointerException ex) {
                        System.out.println(ex.getMessage());
                    }
                }else
                    System.out.println("Invalid Option Choose Again!!");
                break;
            case 9:
                return;
            default:
                System.out.println("Invalid Option Choose Again!!");
        }
        bookMenu();
    }

    private void authorMenu(){
        System.out.println("Enter \n1. Get Authors\n2. Search\n3. Sort");
        if(authenticationService.isAdmin())
            System.out.println("4. Add Author\n5. Remove Author");
        System.out.println("9. Go Back");
        int choice = 0;
        try{
            choice = in.nextInt();in.nextLine();
        }
        catch (InputMismatchException ex){
            System.out.println("Invalid Input!");
            in.nextLine();
            authorMenu();
        }
        switch (choice){
            case 1:
                List<Author> authors = library.getAuthors();
                System.out.println((!authors.isEmpty())?authors:"No Authors Found");
                break;
            case 2:
                System.out.println("Enter Search key");
                authors = library.searchAuthors(in.nextLine());
                System.out.println((!authors.isEmpty())?authors:"No Authors Found with given term");
                break;
            case 3:
                int src = authorService.sortChoice();
                if(src != 0) {
                    authors = library.sortAuthors(src);
                    System.out.println((!authors.isEmpty())?authors:"No Authors Found");
                }
                break;
            case 4:
                if(authenticationService.isAdmin()) {
                    library.addAuthor(authorService.create());
                    System.out.println("Author Added Successfully");
                }else
                    System.out.println("Invalid Option Choose Again!!");
                break;
            case 5:
                if(authenticationService.isAdmin()) {
                    try {
                        int id = authorService.removeId();
                        if(id == -1){
                            System.out.println("No Authors Found");
                            break;
                        }
                        library.removeAuthor(id);
                        System.out.println("Author Removed Successfully");
                    } catch (NullPointerException ex) {
                        System.out.println(ex.getMessage());
                    }
                }else
                    System.out.println("Invalid Option Choose Again!!");
                break;
            case 9:
                return;
            default:
                System.out.println("Invalid Option Choose Again!!");
        }
        authorMenu();
    }

    private void borrowerMenu(){
        System.out.println("Enter \n1. Add Borrower\n2. Remove Borrower\n3. Get Borrowers\n4. Search\n5. Sort\n6. Go Back");
        int choice = 0;
        try{
            choice = in.nextInt();in.nextLine();
        }
        catch (InputMismatchException ex){
            System.out.println("Invalid Input!");
            in.nextLine();
            borrowerMenu();
        }
        switch (choice){
            case 1:
                try {
                    library.addBorrower(borrowerService.create());
                    System.out.println("Borrower Added Successfully");
                }catch (NullPointerException ex){
                    System.out.println(ex.getMessage());
                }
                break;
            case 2:
                try {
                    String id = borrowerService.removeId();
                    if(id.isEmpty()){
                        System.out.println("No Borrowers Found");
                        break;
                    }
                    library.removeBorrower(id);
                    System.out.println("Borrower Removed Successfully");
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case 3:
                List<Borrower> borrowers = library.getBorrowers();
                System.out.println((!borrowers.isEmpty())?borrowers:"No Borrowers Found");
                break;
            case 4:
                System.out.println("Enter Search key");
                borrowers = library.searchBorrowers(in.nextLine());
                System.out.println((!borrowers.isEmpty())?borrowers:"No Borrowers Found");
                break;
            case 5:
                borrowers = library.sortBorrowers();
                System.out.println((!borrowers.isEmpty())?borrowers:"No Borrowers Found");
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid Option Choose Again!!");
        }
        borrowerMenu();
    }

    private void librarymenu() {
        System.out.println("Enter");
        if(!authenticationService.isAdmin())
            System.out.println("1. Borrow Book \n2. Return Book");
        else
            System.out.println("3. History");
        System.out.println("9. Go Back");
        int choice = 0;
        try{
            choice = in.nextInt();in.nextLine();
        }
        catch (InputMismatchException ex){
            System.out.println("Invalid Input!");
            in.nextLine();
            librarymenu();
        }
        switch (choice){
            case 1:
                if(!authenticationService.isAdmin()) {
                    try {
                        library.borrow(borrowerDetailsService.create());
                        System.out.println("Book Borrowed!!");
                    }
                    catch (IllegalStateException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                else 
                    System.out.println("Invalid Option Choose Again!!");
                break;
            case 2:
                if(!authenticationService.isAdmin()) {
                    try {
                        library.returnBook(borrowerDetailsService.returnId());
                        System.out.println("Book Returned!!");
                    } catch (IllegalAccessException | NullPointerException ex) {
                        System.out.println(ex.getMessage());
                        ;
                    }
                }else
                    System.out.println("Invalid Option Choose Again!!");
                break;
            case 3:
                if(authenticationService.isAdmin()) {
                    List<BorrowDetails> bdl = library.track();
                    System.out.println((!bdl.isEmpty())?bdl:"History is Empty");
                }
                else
                    System.out.println("Invalid Option Choose Again!!");
                break;
            case 9:
                return;
            default:
                System.out.println("Invalid Option Choose Again!!");
        }
        librarymenu();
    }

    public int mainMenu(){
        System.out.println("Enter\n1. Book Services\n2. Author Services\n3. Library Services");
        if(authenticationService.isAdmin())
            System.out.println("4. Borrower Service");
        System.out.println("9. Logout\n0. Exit");
        int choice;
        try {
            choice = in.nextInt();in.nextLine();
        }
        catch (InputMismatchException ex){
            System.out.println("Enter Valid Input: ");
            in.nextLine();
            return mainMenu();
        }
        switch (choice){
            case 1:
                bookMenu();
                break;
            case 2:
                authorMenu();
                break;
            case 3:
                librarymenu();
                break;
            case 4:
                if(authenticationService.isAdmin())
                    borrowerMenu();
                else return -1;
                break;
            case 9:
                authenticationService.logout();
                return -2;
            case 0:
                return choice;
            default:
                return -1;
        }
        return choice;
    }
}
