package services;

import java.util.Scanner;

public class SingletonScanner {
    private static Scanner in;
    public static Scanner getInstance(){
        if(in == null)  in = new Scanner(System.in);
        return in;
    }
}
