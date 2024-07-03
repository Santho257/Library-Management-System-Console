package services;

import models.Author;
import models.Book;
import models.Genre;
import repos.AuthorRepository;
import repos.AuthorRepositoryImpl;
import repos.BookRepository;
import repos.BookRepositoryImpl;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private static BookService bookService;
    private final Scanner in = SingletonScanner.getInstance();


    private BookService(){
        bookRepository = BookRepositoryImpl.getInstance();
        authorRepository = AuthorRepositoryImpl.getInstance();
    }

    public static BookService getInstance(){
        if(bookService == null) bookService = new BookService();
        return bookService;
    }

    public Book createBook(){
        System.out.println("Enter Book Title: ");
        String title = in.nextLine();
        int authorId;
        Author author = null;
        List<Author> authorList = authorRepository.get();
        if(authorList.isEmpty())    throw new NullPointerException("No Authors Found");
        while(true){
            try{
                System.out.println("Choose Book Author: \n"+authorList);
                System.out.println("Enter -1 to cancel");
                authorId = in.nextInt();in.nextLine();
                if(authorId == -1)    throw new IllegalStateException("");
                author = authorRepository.get(authorId);
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
        Genre genre;
        while(true) {
            try {
                System.out.println("Enter Genre: " + Arrays.toString(Genre.values()));
                System.out.println("Enter cancel to cancel");
                String genreStr = in.nextLine().toUpperCase();
                if(genreStr.equals("CANCEL")) throw new IllegalStateException("");
                genre = Genre.valueOf(genreStr);
                break;
            }
            catch (IllegalArgumentException | NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        }
        int pages;
        while(true){
            try{
                System.out.println("Enter pages\nEnter -1 to cancel");
                pages = in.nextInt();in.nextLine();
                if(pages == -1) throw new IllegalStateException();
                if(pages < 0)   throw new IllegalAccessException("Pages cannot be less than 1");
                break;
            }
            catch (InputMismatchException ex){
                System.out.println("Enter Valid Number: ");
                in.nextLine();
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        return new Book(title, author, genre, pages);
    }

    public int removeId() {
        int id;
        try {
            List<Book> books = bookRepository.get();
            if(books.isEmpty()) return -1;
            System.out.println("Choose the book Id to get Removed: \n" + books);
            System.out.println("Enter -1 to exit");
            id = in.nextInt();
            if(id == -1)    throw new NullPointerException();
        }
        catch (InputMismatchException ex){
            System.out.println("Enter Valid Number");
            return removeId();
        }
        return id;
    }

    public int searchChoice() {
        System.out.println("Enter to Search By\n1. By Title\n2. By Author\n3. By Genre\n0. Back");
        int choice;
        try{
            choice = in.nextInt();in.nextLine();
        }catch (InputMismatchException ex){
            System.out.println("Enter Numbers: ");
            in.nextLine();
            return searchChoice();
        }
        if(choice > 3 || choice < 0){
            System.out.println("Invalid Choice!!");
            return searchChoice();
        }
        return choice;
    }
    public int sortChoice() {
        System.out.println("Enter to Sort By\n1. By Title\n2. By Author\n3. By Publication Date\n0. Back");
        int choice;
        try{
            choice = in.nextInt();in.nextLine();
        }catch (InputMismatchException ex){
            System.out.println("Enter Numbers: ");
            in.nextLine();
            return sortChoice();
        }
        if(choice > 3 || choice < 0){
            System.out.println("Invalid Choice!!");
            return sortChoice();
        }
        return choice;
    }
}
