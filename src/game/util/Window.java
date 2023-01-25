package game.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The class Window extends the class JFrame and holds the methods for creating the main look of the application.
 */
public class Window extends JFrame {
    static JButton [][] buttons;
    static int lastX;
    static int lastY;
    static String indicator = "X";
    static String computerIndicator = "O";
    /**
     * The method game sets the default layout for the application as well as creates all the buttons using a 2d-array.
     * @param size The users given size input used to determine how big the 2d-array is and how many buttons there are in the application.
     * @param winCount The users given input for how many same markers have to be next to each other for someone to win.
     */
    public void game(int size, int winCount) {
        buttons = new JButton[size][size];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                button.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 300/size));
                button.setBackground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                button.setName(i + "" + j);
                buttons[i][j] = button;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean computerTurn = true;
                        clickAction(button, indicator);
                        if (WinChecker.winCondition(winCount, buttons, indicator)) {
                            JOptionPane.showMessageDialog(Window.this, "The player wins!");
                            computerTurn = false;
                            Window.this.dispose();
                        } else if (WinChecker.tieCondition(buttons)) {
                            JOptionPane.showMessageDialog(Window.this, "The game ended in a tie!");
                            computerTurn = false;
                            Window.this.dispose();
                        }
                        if (computerTurn) {
                            buttons = AI.computerTurn(winCount, indicator, computerIndicator, buttons, lastY, lastX);
                            if (WinChecker.winCondition(winCount, buttons, computerIndicator)) {
                                JOptionPane.showMessageDialog(Window.this, "The computer wins!");
                                Window.this.dispose();
                            } else if (WinChecker.tieCondition(buttons)) {
                                JOptionPane.showMessageDialog(Window.this, "The game ended in a tie!");
                                Window.this.dispose();
                            }
                        }
                    }
                });
                add(button);
            }
        }
    }
    /**
     * The method clickAction sets the buttons text to player's marker, disables the button and records the position of the clicked button
     * when the user clicks one of the buttons in the application.
     * @param button The JButton that the user is currently clicking.
     * @param indicator The String variable that will be put on the button as a text. In this case it is player's marker "X".
     */
    public void clickAction(JButton button, String indicator) {
        button.setText(indicator);
        button.setEnabled(false);
        lastY = Character.getNumericValue(button.getName().charAt(0));
        lastX = Character.getNumericValue(button.getName().charAt(1));
    }
}