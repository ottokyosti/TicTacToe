package game.util;

import javax.swing.*;

/**
 * The class WinChecker contains the methods for checking a possible win. It can be used to determine if either the player or the computer wins. It will
 * also check for a possible tie.
 */
public class WinChecker {
    /**
     * The method winCondition is the main method for checking for a win. It uses the main 2d-array where all the buttons are, goes through
     * every possible marker and checks the four main directions where the win might occur. Because the method checks every marker starting
     * from the top, it only has to check for a win to the right, down, diagonally downright and diagonally downleft. 
     * @param winCount The user's given input for how many markers must be next to each other to win.
     * @param buttons The 2d-array where all the buttons are stored.
     * @param indicator The String variable that can be either computer's marker "O" or player's marker "X".
     * @return A boolean value true if either computer or player has necessary markers in line for a win and false if neither has a win condition.
     */
    public static boolean winCondition(int winCount, JButton [][] buttons, String indicator) {
        int [] winArray = new int[4];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].getText().equals(indicator)) {
                    for (int k = j; k < winCount + j; k++) {
                        if (k < buttons[j].length) {
                            if (buttons[i][k].getText().equals(indicator)) {
                                winArray[0]++;
                            } else {
                                winArray[0] = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    for (int k = i; k < winCount + i; k++) {
                        if (k < buttons.length) {
                            if (buttons[k][j].getText().equals(indicator)) {
                                winArray[1]++;
                            } else {
                                winArray[1] = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    for (int k = 0; k < winCount; k++) {
                        if (k + i < buttons.length && k + j < buttons[j].length) {
                            if (buttons[i + k][j + k].getText().equals(indicator)) {
                                winArray[2]++;
                            } else {
                                winArray[2] = 0;
                            }
                        } else {
                            break;
                        }
                    }
                    for (int k = 0; k < winCount; k++) {
                        if (i + k < buttons.length && j - k >= 0) {
                            if (buttons[i + k][j - k].getText().equals(indicator)) {
                                winArray[3]++;
                            } else {
                                winArray[3] = 0;
                            }
                        } else {
                            break;
                        }
                    }
                }
                for (int l = 0; l < winArray.length; l++) {
                    if (winArray[l] == winCount) {
                        return true;
                    }
                    winArray[l] = 0;
                }
            }
        }
        return false;
    }
    /**
     * The method tieCondition checks the whole 2d-array for a button that is enabled. If it finds it, it will determine that there is still
     * buttons to press and the game cannot be a tie and if it doesn't find it, it will determine that there is no buttons to press so the game
     * must be in a tie.
     * @param buttons The main 2d-array that contains all the buttons.
     * @return A boolean value of true if there is a tie and false if there isn't a tie.
     */
    public static boolean tieCondition(JButton [][] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (buttons[i][j].isEnabled()) {
                    return false;
                }
            }
        }
        return true;
    }
}