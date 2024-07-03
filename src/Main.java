import services.*;

public class Main {
    private static final MenuService menuService = MenuService.getInstance();
    private static final AuthenticationService authenticationService = AuthenticationService.getInstance();

    public static void main(String[] args){
        System.out.println("***BookRepository Management Application***");
        int choice;
        do{
            choice = authenticationService.authenticationMenu();
            if(choice == -1)    return;
        }while(choice != 1);
        int choice2;
        do{
            choice2 = menuService.mainMenu();
            if(choice2 == -1) System.out.println("Invalid Input!!");
            else if(choice2 == -2)   main(args);
        }while(choice2 != 0);
    }
}