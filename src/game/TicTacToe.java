package game;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalButtonUI;
import java.io.Console;
import java.awt.*;
import java.awt.Dimension;
import game.util.Window;

/**
 * The class TicTacToe contains the main initialization of the application as well as exception handling for user's input
 */
public class TicTacToe {
    /**
     * The main method initializes the application and asks from the user the size and how many markers must be next to each other for a win.
     * @param args An array where you can put String variables straight from command line (not used in this application).
     */
    public static void main(String [] args) {
        UIManager.put("Button.disabledText", new ColorUIResource(Color.BLACK));
        Window win = new Window();
        win.setTitle("TicTacToe");
        System.out.println("Welcome to Tictactoe!");
        System.out.println("How big do you want the gameboard to be? (It will be a rectangle)");
        int size = sizeExceptionHandling();
        System.out.println("How many game pieces must be in a row to win the game?");
        int winCount = winExceptionHandling(size);
        win.game(size, winCount);
        win.setSize(new Dimension(650, 650));
        win.setResizable(true);
        win.setVisible(true);
    }
    /**
     * This method handles the exception handling of the given size input. The user cannot give any other data types than integers
     * and the size cannot be less than or equal to zero. This method will also inform the user if the given value is so large
     * that it will affect how the application looks.
     * @return The user's given size input if it is within the method's parameters.
     */
    public static int sizeExceptionHandling() {
        Console c = System.console();
        boolean handling = true;
        while (handling) {
            try {
                int number = Integer.parseInt(c.readLine()); 
                if (number <= 0) {
                    System.out.println("The size cannot be lesser than or equal to 0, try again!");
                } else if (number >= 50) {
                    boolean innerTry = true;
                    while (innerTry) {
                        System.out.println("Your chosen size might affect how the game pieces will look on screen. Do you want to continue anyway? (y/n)");
                        String answer = c.readLine();
                        if (answer.equals("y")) {
                            innerTry = false;
                            return number;
                        } else if (answer.equals("n")) {
                            System.out.println("Then, give me number for the size of the gameboard");
                            innerTry = false;
                        }
                    }
                } else {
                    handling = false;
                    return number;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Please, give an actual number");
            }
        }
        return 3;
    }
    /**
     * This method handles the exception handling of the given winning count input. This method differs from the size exception handling method
     * by having a parameter. This method will not return the user's input if it is less than or equal to 0 or it is greater than user's given
     * size input. User also cannot give any other data type variables than integer variables and depending if the size input is greater than or 
     * equal to 10, the user cannot give a number that is less than 5.
     * @param size The user's size input given earlier.
     * @return The user's given input for win condition, if it is within the method's parameters.
     */
    public static int winExceptionHandling(int size) {
        Console c = System.console();
        boolean handling = true;
        while (handling) {
            try {
                int number = Integer.parseInt(c.readLine());
                if (number <= 0 ||number > size) {
                    System.out.println("The number cannot be outside the gameboard parameters! (Must be higher than 0 and lesser than or equal to " + size);
                } else if (size >= 10 && number < 5) {
                    System.out.println("From your chosen size and onwards the win count must be higher or equal to 5");
                } else {
                    handling = false;
                    return number;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Please, give an actual number");
            }
        }
        return 3;
    }
}