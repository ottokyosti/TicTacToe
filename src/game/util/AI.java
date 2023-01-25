package game.util;

import javax.swing.*;

/**
 * The class AI holds the methods for determining what move will the computer make. This class defines the computer's artificial intelligence.
 * The class AI's methods will help the computer place a marker if itself is about to win or if the player is about to win.
 */
public class AI {
    static int aiY;
    static int aiX;
    static int wayChooser = (int) (Math.random() * 8);
    /**
     * The computerTurn method is the main deciding factor in what the computer's next turn will be. If the computer is about to win in the next turn, then it will
     * try to win. If the player is about to win in the next turn, then the computer will block it. If there isn't a computer's marker in
     * the board yet, then it will place one in a random place. If none of these conditions apply, then it will choose a random direction
     * and place markers next to each other until one of previously mentioned conditions apply.
     * @param winCount The user's given input for how many markers must be next to each other to win.
     * @param playerIndicator The String variable containing player's marker "X".
     * @param computerIndicator The String variable containing computer's marker "O".
     * @param buttons The main 2d-array where all the buttons are stored.
     * @param yCheck The last position of the player's last clicked button in the y-axis.
     * @param xCheck The last position of the player's last clicked button in the x-axis.
     * @return The 2d-array buttons modified by the AI class methods.
     */
    public static JButton[][] computerTurn(int winCount, String playerIndicator, String computerIndicator, JButton [][] buttons, int yCheck, int xCheck) {
        if (aiCheckHelper(buttons, winCount, computerIndicator, playerIndicator, aiY, aiX) != -1) {
            buttons = aiActionPlacement(buttons, winCount, computerIndicator, playerIndicator, aiY, aiX);
        } else if (aiCheckHelper(buttons, winCount, playerIndicator, computerIndicator, yCheck, xCheck) != -1) {
            buttons = aiActionPlacement(buttons, winCount, playerIndicator, computerIndicator, yCheck, xCheck);
        } else if (firstPlacement(buttons, computerIndicator)) {
            buttons = defaultPlacement(buttons, computerIndicator);
        } else {
            buttons = aiNoActionPlacement(buttons, computerIndicator, aiY, aiX);
        }
        return buttons;
    }
    /**
     * The method aiActionPlacement is a method that places a marker for computer if the computer is one marker away from winning 
     * or if the player is one marker away from winning. It uses a method called aiCheckHelper to get a number which dictates the
     * computers next move.
     * @param buttons The main 2d-array where all the buttons are stored.
     * @param winCount The user's input for how many markers must be next to each other to win.
     * @param indicator A String variable that is counted to check for a win. Can be either computer's or player's.
     * @param opponentIndicator A String variable that will break the count for a win if there is one. Can be either computer's or player's.
     * @param yCheck The last known position of a marker on the y-axis. Can be either computer's or player's.
     * @param xCheck The last known position of a marker on the x-axis. Can be either computer's or player's.
     * @return The 2d-array in which the computer has placed a marker.
     */
    public static JButton[][] aiActionPlacement(JButton[][] buttons, int winCount, String indicator, String opponentIndicator, int yCheck, int xCheck) {
        String computerIndicator = "O";
        switch (aiCheckHelper(buttons, winCount, indicator, opponentIndicator, yCheck, xCheck)) {
            case 0:
                for (int i = 0; i < winCount; i++) {
                    if (yCheck - i >= 0 && xCheck - i >= 0) {
                        if (buttons[yCheck - i][xCheck - i].isEnabled()) {
                            buttons[yCheck - i][xCheck - i].setText(computerIndicator);
                            buttons[yCheck - i][xCheck - i].setEnabled(false);
                            aiY = yCheck - i;
                            aiX = xCheck - i;
                            return buttons;
                        }
                    }
                }
                if (yCheck + 1 < buttons.length && xCheck + 1 < buttons[0].length && buttons[yCheck + 1][xCheck + 1].isEnabled()) {
                    buttons[yCheck + 1][xCheck + 1].setText(computerIndicator);
                    buttons[yCheck + 1][xCheck + 1].setEnabled(false);
                    aiY = yCheck + 1;
                    aiX = xCheck + 1;
                } else {
                    buttons = aiNoActionPlacement(buttons, computerIndicator, yCheck, xCheck);
                }
                break;
            case 1:
                for (int i = 0; i < winCount; i++) {
                    if (yCheck - i >= 0) {
                        if (buttons[yCheck - i][xCheck].isEnabled()) {
                            buttons[yCheck - i][xCheck].setText(computerIndicator);
                            buttons[yCheck - i][xCheck].setEnabled(false);
                            aiY = yCheck - i;
                            aiX = xCheck;
                            return buttons;
                        }
                    }
                }
                if (yCheck + 1 < buttons.length && buttons[yCheck + 1][xCheck].isEnabled()) {
                    buttons[yCheck + 1][xCheck].setText(computerIndicator);
                    buttons[yCheck + 1][xCheck].setEnabled(false);
                    aiY = yCheck + 1;
                    aiX = xCheck;
                } else {
                    buttons = aiNoActionPlacement(buttons, computerIndicator, yCheck, xCheck);
                }
                break;
            case 2:
                for (int i = 0; i < winCount; i++) {
                    if (yCheck - i >= 0 && xCheck + i < buttons[0].length) {
                        if (buttons[yCheck - i][xCheck + i].isEnabled()) {
                            buttons[yCheck - i][xCheck + i].setText(computerIndicator);
                            buttons[yCheck - i][xCheck + i].setEnabled(false);
                            aiY = yCheck - i;
                            aiX = xCheck + i;
                            return buttons;
                        }
                    }
                }
                if (yCheck + 1 < buttons.length && xCheck - 1 >= 0 && buttons[yCheck + 1][xCheck - 1].isEnabled()) {
                    buttons[yCheck + 1][xCheck - 1].setText(computerIndicator);
                    buttons[yCheck + 1][xCheck - 1].setEnabled(false);
                    aiY = yCheck + 1;
                    aiX = xCheck - 1;
                } else {
                    buttons = aiNoActionPlacement(buttons, computerIndicator, yCheck, xCheck);
                }
                break;
            case 3:
                for (int i = 0; i < winCount; i++) {
                    if (xCheck + i < buttons[0].length) {
                        if (buttons[yCheck][xCheck + i].isEnabled()) {
                            buttons[yCheck][xCheck + i].setText(computerIndicator);
                            buttons[yCheck][xCheck + i].setEnabled(false);
                            aiY = yCheck;
                            aiX = xCheck + i;
                            return buttons;
                        }
                    }
                }
                if (xCheck - 1 >= 0 && buttons[yCheck][xCheck - 1].isEnabled()) {
                    buttons[yCheck][xCheck - 1].setText(computerIndicator);
                    buttons[yCheck][xCheck - 1].setEnabled(false);
                    aiY = yCheck;
                    aiX = xCheck - 1;
                } else {
                    buttons = aiNoActionPlacement(buttons, computerIndicator, yCheck, xCheck);
                }
                break;
            case 4:
                for (int i = 0; i < winCount; i++) {
                    if (yCheck + i < buttons.length && xCheck + i < buttons[0].length) {
                        if (buttons[yCheck + i][xCheck + i].isEnabled()) {
                            buttons[yCheck + i][xCheck + i].setText(computerIndicator);
                            buttons[yCheck + i][xCheck + i].setEnabled(false);
                            aiY = yCheck + i;
                            aiX = xCheck + i;
                            return buttons;
                        }
                    }
                }
                if (yCheck - 1 >= 0 && xCheck - 1 >= 0 && buttons[yCheck - 1][xCheck - 1].isEnabled()) {
                    buttons[yCheck - 1][xCheck - 1].setText(computerIndicator);
                    buttons[yCheck - 1][xCheck - 1].setEnabled(false);
                    aiY = yCheck - 1;
                    aiX = xCheck - 1;
                } else {
                    buttons = aiNoActionPlacement(buttons, computerIndicator, yCheck, xCheck);
                }
                break;
            case 5:
                for (int i = 0; i < winCount; i++) {
                    if (yCheck + i < buttons.length) {
                        if (buttons[yCheck + i][xCheck].isEnabled()) {
                            buttons[yCheck + i][xCheck].setText(computerIndicator);
                            buttons[yCheck + i][xCheck].setEnabled(false);
                            aiY = yCheck + i;
                            aiX = xCheck;
                            return buttons;
                        }
                    }
                }
                if (yCheck - 1 >= 0 && buttons[yCheck - 1][xCheck].isEnabled()) {
                    buttons[yCheck - 1][xCheck].setText(computerIndicator);
                    buttons[yCheck - 1][xCheck].setEnabled(false);
                    aiY = yCheck - 1;
                    aiX = xCheck;
                } else {
                    buttons = aiNoActionPlacement(buttons, computerIndicator, yCheck, xCheck);
                }
                break;
            case 6:
                for (int i = 0; i < winCount; i++) {
                    if (yCheck + i < buttons.length && xCheck - i >= 0) {
                        if (buttons[yCheck + i][xCheck - i].isEnabled()) {
                            buttons[yCheck + i][xCheck - i].setText(computerIndicator);
                            buttons[yCheck + i][xCheck - i].setEnabled(false);
                            aiY = yCheck + i;
                            aiX = xCheck - i;
                            return buttons;
                        }
                    }
                }
                if (yCheck - 1 >= 0 && xCheck + 1 < buttons[0].length && buttons[yCheck - 1][xCheck + 1].isEnabled()) {
                    buttons[yCheck - 1][xCheck + 1].setText(computerIndicator);
                    buttons[yCheck - 1][xCheck + 1].setEnabled(false);
                    aiY = yCheck - 1;
                    aiX = xCheck + 1;
                } else {
                    buttons = aiNoActionPlacement(buttons, computerIndicator, yCheck, xCheck);
                }
                break;
            case 7:
                for (int i = 0; i < winCount; i++) {
                    if (xCheck - i >= 0) {
                        if (buttons[yCheck][xCheck - i].isEnabled()) {
                            buttons[yCheck][xCheck - i].setText(computerIndicator);
                            buttons[yCheck][xCheck - i].setEnabled(false);
                            aiY = yCheck;
                            aiX = xCheck - i;
                            return buttons;
                        }
                    }
                }
                if (xCheck + 1 < buttons[0].length && buttons[yCheck][xCheck + 1].isEnabled()) {
                    buttons[yCheck][xCheck + 1].setText(computerIndicator);
                    buttons[yCheck][xCheck + 1].setEnabled(false);
                    aiY = yCheck;
                    aiX = xCheck + 1;
                } else {
                    buttons = aiNoActionPlacement(buttons, computerIndicator, yCheck, xCheck);
                }
                break;
            default:
                buttons = defaultPlacement(buttons, computerIndicator);
        }
        return buttons;
    }
    /**
     * The method aiNoActionPlacement kicks in when neither computer or player is about to win. It will get a random number, which
     * will dictate the direction where the computer will start to place it's markers. If it encounters a player marker or the egde of
     * the array, it will generate a new random direction and continue placing markers. It also contains a loopstopper for situations where
     * the computer cannot find any direction to go to. When it has looped for about 50 times without a resolution, the computer will use
     * the defaultPlacement method for it's next move.
     * @param buttons The main 2d-array where all the buttons are stored.
     * @param computerIndicator The String variable of the marker that will be put on the button.
     * @param yCheck The last position of the computer's last placed marker on the y-axis.
     * @param xCheck The last position of the computer's last placed marker on the x-axis.
     * @return The 2d-array of buttons that the computer has edited.
     */
    public static JButton[][] aiNoActionPlacement(JButton[][] buttons, String computerIndicator, int yCheck, int xCheck) {
        boolean switchRun = true;
        int loopStopper = 0;
        while (switchRun) {
            loopStopper++;
            switch (wayChooser) {
                case 0:
                    if (xCheck + 1 < buttons[0].length && buttons[yCheck][xCheck + 1].isEnabled()) {
                        if (buttons[yCheck][xCheck + 1].getText().equals("")) {
                            buttons[yCheck][xCheck + 1].setText(computerIndicator);
                            buttons[yCheck][xCheck + 1].setEnabled(false);
                            wayChooser = 0;
                            aiY = yCheck;
                            aiX = xCheck + 1;
                            switchRun = false;
                        } else {
                            wayChooser = (int) (Math.random() * 8);
                        }
                    } else {
                        wayChooser = (int) (Math.random() * 8);
                    }
                    break;
                case 1:
                    if (yCheck + 1 < buttons.length && buttons[yCheck + 1][xCheck].isEnabled()) {
                        if (buttons[yCheck + 1][xCheck].getText().equals("")) {
                            buttons[yCheck + 1][xCheck].setText(computerIndicator);
                            buttons[yCheck + 1][xCheck].setEnabled(false);
                            wayChooser = 1;
                            aiY = yCheck + 1;
                            aiX = xCheck;
                            switchRun = false;
                        } else {
                            wayChooser = (int) (Math.random() * 8);
                        }
                    } else {
                        wayChooser = (int) (Math.random() * 8);
                    }
                    break;
                case 2:
                    if (xCheck - 1 >= 0 && buttons[yCheck][xCheck - 1].isEnabled()) {
                        if (buttons[yCheck][xCheck - 1].getText().equals("")) {
                            buttons[yCheck][xCheck - 1].setText(computerIndicator);
                            buttons[yCheck][xCheck - 1].setEnabled(false);
                            wayChooser = 2;
                            aiY = yCheck;
                            aiX = xCheck - 1;
                            switchRun = false;
                        } else {
                            wayChooser = (int) (Math.random() * 8);
                        }
                    } else {
                        wayChooser = (int) (Math.random() * 8);
                    }
                    break;
                case 3:
                    if (yCheck - 1 >= 0 && buttons[yCheck - 1][xCheck].isEnabled()) {
                        if (buttons[yCheck - 1][xCheck].getText().equals("")) {
                            buttons[yCheck - 1][xCheck].setText(computerIndicator);
                            buttons[yCheck - 1][xCheck].setEnabled(false);
                            wayChooser = 3;
                            aiY = yCheck - 1;
                            aiX = xCheck;
                            switchRun = false;
                        } else {
                            wayChooser = (int) (Math.random() * 8);
                        }
                    } else {
                        wayChooser = (int) (Math.random() * 8);
                    }
                    break;
                case 4:
                    if (yCheck - 1 >= 0 && xCheck + 1 < buttons[0].length && buttons[yCheck - 1][xCheck + 1].isEnabled()) {
                        if (buttons[yCheck - 1][xCheck + 1].getText().equals("")) {
                            buttons[yCheck - 1][xCheck + 1].setText(computerIndicator);
                            buttons[yCheck - 1][xCheck + 1].setEnabled(false);
                            wayChooser = 4;
                            aiY = yCheck - 1;
                            aiX = xCheck + 1;
                            switchRun = false;
                        } else {
                            wayChooser = (int) (Math.random() * 8);
                        }
                    } else {
                        wayChooser = (int) (Math.random() * 8);
                    }
                    break;
                case 5:
                    if (yCheck + 1 < buttons.length && xCheck + 1 < buttons[0].length && buttons[yCheck + 1][xCheck + 1].isEnabled()) {
                        if (buttons[yCheck + 1][xCheck + 1].getText().equals("")) {
                            buttons[yCheck + 1][xCheck + 1].setText(computerIndicator);
                            buttons[yCheck + 1][xCheck + 1].setEnabled(false);
                            wayChooser = 5;
                            aiY = yCheck + 1;
                            aiX = xCheck + 1;
                            switchRun = false;
                        } else {
                            wayChooser = (int) (Math.random() * 8);
                        }
                    } else {
                        wayChooser = (int) (Math.random() * 8);
                    }
                    break;
                case 6:
                    if (yCheck + 1 < buttons.length && xCheck - 1 >= 0 && buttons[yCheck + 1][xCheck - 1].isEnabled()) {
                        if (buttons[yCheck + 1][xCheck - 1].getText().equals("")) {
                            buttons[yCheck + 1][xCheck - 1].setText(computerIndicator);
                            buttons[yCheck + 1][xCheck - 1].setEnabled(false);
                            wayChooser = 6;
                            aiY = yCheck + 1;
                            aiX = xCheck - 1;
                            switchRun = false;
                        } else {
                            wayChooser = (int) (Math.random() * 8);
                        }
                    } else {
                        wayChooser = (int) (Math.random() * 8);
                    }
                    break;
                case 7:
                    if (yCheck - 1 >= 0 && xCheck - 1 >= 0 && buttons[yCheck - 1][xCheck - 1].isEnabled()) {
                        if (buttons[yCheck - 1][xCheck - 1].getText().equals("")) {
                            buttons[yCheck - 1][xCheck - 1].setText(computerIndicator);
                            buttons[yCheck - 1][xCheck - 1].setEnabled(false);
                            wayChooser = 7;
                            aiY = yCheck - 1;
                            aiX = xCheck - 1;
                            switchRun = false;
                        } else {
                            wayChooser = (int) (Math.random() * 8);
                        }
                    } else {
                        wayChooser = (int) (Math.random() * 8);
                    }
            }
            if (loopStopper > 50) {
                buttons = defaultPlacement(buttons, computerIndicator);
                switchRun = false;
            }
        }
        return buttons;
    }
    /**
     * The defaultPlacement method is used as a failsafe and when the application starts. It will generate two random numbers and place
     * the computer's marker in those coordinates. It is used only when the aiNoActionPlacement method cannot find a free space to put
     * a marker and when the application first starts and there is no computer's marker yet on the board.
     * @param buttons The main 2d-array where all the buttons are stored.
     * @param computerIndicator The String variable of a computer's marker.
     * @return The 2d-array where computer has put it's marker.
     */
    public static JButton[][] defaultPlacement(JButton [][] buttons, String computerIndicator) {
        boolean run = true;
        while (run) {
            int randomY = (int) (Math.random() * buttons.length);
            int randomX = (int) (Math.random() * buttons[0].length);
            if (buttons[randomY][randomX].getText().equals("")) {
                buttons[randomY][randomX].setText(computerIndicator);
                buttons[randomY][randomX].setEnabled(false);
                aiY = randomY;
                aiX = randomX;
                run = false;
            }
        }
        return buttons;
    }
    /**
     * The method aiCheckHelper is the main decider when it comes to what the computer will do next. It gets the position of the last placed
     * marker and from there will check every direction for a possible win. If it finds it, the method will return a number corresponding
     * a direction where the win would occur. If it doesn't find a possible win, the method instead will return a default number which
     * tells other methods that there wasn't a possible win.
     * @param buttons The main 2d-array where all the buttons are stored.
     * @param winCount The user's input for how many markers must be next to each other to win.
     * @param indicator The String variable that will count towards possible win.
     * @param opponentIndicator The String variable that will break the count when found.
     * @param yCheck The last position of the last placed marker (be it computer's or player's) on the y-axis.
     * @param xCheck The last position of the last placed marker (be it computer's or player's) on the x-axis.
     * @return An integer corresponding to one of the directions that other methods will use or the default integer.
     */
    public static int aiCheckHelper(JButton[][] buttons, int winCount, String indicator, String opponentIndicator, int yCheck, int xCheck) {
        int [] counterArray = new int[8];
        for (int i = 0; i < winCount; i++) {
            if (yCheck - i >= 0 && xCheck - i >= 0) {
                if (buttons[yCheck - i][xCheck - i].getText().equals(indicator)) {
                    counterArray[0]++;
                } else if (buttons[yCheck - i][xCheck - i].getText().equals(opponentIndicator)) {
                    counterArray[0] = 0;
                }
                if ((yCheck - (winCount - 1) < 0 || xCheck - (winCount - 1) < 0) && (yCheck + 1 >= buttons.length || xCheck + 1 >= buttons[0].length)) {
                    counterArray[0] = 0;
                }
            }
            if (yCheck - i >= 0) {
                if (buttons[yCheck - i][xCheck].getText().equals(indicator)) {
                    counterArray[1]++;
                } else if (buttons[yCheck - i][xCheck].getText().equals(opponentIndicator)) {
                    counterArray[1] = 0;
                }
                if (yCheck + 1 < buttons.length) {
                    if (buttons[yCheck + 1][xCheck].getText().equals(opponentIndicator)) {
                        counterArray[1] = 0;
                    }
                }
            }
            if (yCheck - i >= 0 && xCheck + i < buttons[0].length) {
                if (buttons[yCheck - i][xCheck + i].getText().equals(indicator)) {
                    counterArray[2]++;
                } else if (buttons[yCheck - i][xCheck + i].getText().equals(opponentIndicator)) {
                    counterArray[2] = 0;
                }
                if ((yCheck - (winCount - 1) < 0 || xCheck + (winCount - 1) >= buttons.length) && (yCheck + 1 >= buttons.length || xCheck - 1 < 0)) {
                    counterArray[2] = 0;
                }
            }
            if (xCheck + i < buttons[0].length) {
                if (buttons[yCheck][xCheck + i].getText().equals(indicator)) {
                    counterArray[3]++;
                } else if (buttons[yCheck][xCheck + i].getText().equals(opponentIndicator)) {
                    counterArray[3] = 0;
                }
                if (xCheck - 1 >= 0) {
                    if (buttons[yCheck][xCheck - 1].getText().equals(opponentIndicator)) {
                        counterArray[3] = 0;
                    }
                }
            }
            if (yCheck + i < buttons.length && xCheck + i < buttons[0].length) {
                if (buttons[yCheck + i][xCheck + i].getText().equals(indicator)) {
                    counterArray[4]++;
                } else if (buttons[yCheck + i][xCheck + i].getText().equals(opponentIndicator)) {
                    counterArray[4] = 0;
                }
                if ((yCheck + (winCount - 1) >= buttons.length || xCheck + (winCount - 1) >= buttons.length) && (yCheck - 1 < 0 || xCheck - 1 < 0)) {
                    counterArray[4] = 0; 
                }
            }
            if (yCheck + i < buttons.length) {
                if (buttons[yCheck + i][xCheck].getText().equals(indicator)) {
                    counterArray[5]++;
                } else if (buttons[yCheck + i][xCheck].getText().equals(opponentIndicator)) {
                    counterArray[5] = 0;
                }
                if (yCheck - 1 >= 0) {
                    if (buttons[yCheck - 1][xCheck].getText().equals(opponentIndicator)) {
                        counterArray[5] = 0;
                    }
                }
            }
            if (yCheck + i < buttons.length && xCheck - i >= 0) {
                if (buttons[yCheck + i][xCheck - i].getText().equals(indicator)) {
                    counterArray[6]++;
                } else if (buttons[yCheck + i][xCheck - i].getText().equals(opponentIndicator)) {
                    counterArray[6] = 0;
                }
                if ((yCheck + (winCount - 1) >= buttons.length || xCheck - (winCount - 1) < 0) && (yCheck - 1 < 0 || xCheck + 1 >= buttons[0].length)) {
                    counterArray[6] = 0;
                }
            }
            if (xCheck - i >= 0) {
                if (buttons[yCheck][xCheck - i].getText().equals(indicator)) {
                    counterArray[7]++;
                } else if (buttons[yCheck][xCheck - i].getText().equals(opponentIndicator)) {
                    counterArray[7] = 0;
                }
                if (xCheck + 1 < buttons[0].length) {
                    if (buttons[yCheck][xCheck + 1].getText().equals(opponentIndicator)) {
                        counterArray[7] = 0;
                    }
                }
            }
        }
        for (int j = 0; j < counterArray.length; j++) {
            if (counterArray[j] >= winCount - 1) {
                return j;
            } 
        }
        return -1;
    }
    /**
     * The method firstPlacement is only used at the start of the application when the computer hasn't put any markers in the array.
     * This method will look for a button with a "O" text and if it doesn't find it, it will return a true statement. If it finds one, it
     * will return a false statement.
     * @param buttons The main 2d-array where all the buttons are stored.
     * @param computerIndicator A String variable indicating the computer's marker.
     * @return A true or false statment whether it finds a button with an "O" text or not.
     */
    public static boolean firstPlacement(JButton [][] buttons, String computerIndicator) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (buttons[i][j].getText().equals(computerIndicator)) {
                    return false;
                }
            }
        }
        return true;
    }
}