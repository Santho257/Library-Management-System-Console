package services;

import models.Author;
import repos.AuthorRepository;
import repos.AuthorRepositoryImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AuthorService {
    private final AuthorRepository authorRepository;
    private static AuthorService authorService;
    private Scanner in = SingletonScanner.getInstance();
    private AuthorService(){
        this.authorRepository = AuthorRepositoryImpl.getInstance();
    }
    public static AuthorService getInstance() {
        if(authorService == null)   authorService = new AuthorService();
        return authorService;
    }

    public Author create(){
        System.out.println("Enter Author Name: ");
        String name = in.nextLine().trim();
        if(!Pattern.matches("([a-zA-Z.\\s]+)+",name)){
            System.out.println("Enter Valid Name: (Word Only Comtains Letters and spaces)");
            return create();
        }
        return new Author(name.toUpperCase());
    }

    public int removeId() {
        int id;
        try {
            List<Author> authors = authorRepository.get();
            if(authors.isEmpty()){
                return -1;
            }
            System.out.println("Choose the Author Id to get Removed:\n"
                    + authors + "\nEnter -1 to cancel");
            id = in.nextInt();
            if(id == -1)    throw new NullPointerException("");
        }
        catch (InputMismatchException ex){
            System.out.println("Enter Valid Number");
            return removeId();
        }
        return id;
    }

    public int sortChoice() {
        System.out.println("Enter to Sort By\n1. By Name\n2. By Book Written\n0. Back");
        int choice;
        try{
            choice = in.nextInt();in.nextLine();
        }catch (InputMismatchException ex){
            System.out.println("Enter Numbers: ");
            in.nextLine();
            return sortChoice();
        }
        if(choice > 2 || choice < 0){
            System.out.println("Invalid Choice!!");
            return sortChoice();
        }
        return choice;
    }
}
