package com.company;
import java.lang.*;
import java.util.Scanner;
import java.lang.String;

/**
 * Main class
 */
public class Main {
    /**
     * Main method
     * @param args main arguments
     */
    public static void main(String[] args) {
        boolean t;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the expression: ");
        String str = in.nextLine();

        Calculator exp = new Calculator(str);
        t = exp.count();

        if( !t)
        {
            System.out.print("The expression is incorrect.");
        }
        else{
            System.out.print(str + " = ");
            System.out.print(exp);
        }
    }
}
