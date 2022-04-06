import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Creates a JPanel with only the buttons of the Game
 */

public class Playground extends JPanel{

    private int         charStart   = 0;
    private JButton[][] aButtons    = new JButton[11][11];
    private int[][]     aShips      = new int[aButtons.length-1][aButtons[0].length-1]; // 0 = default; 1 = ship; 2 = broken ship
    private Icon        mine        = new ImageIcon("pictures/mine.png");
    //private Image       img         ;


    Playground(GameField gameField, GameSummary gameSummary) {
        setLayout(new GridLayout(aButtons.length, aButtons[0].length));
        setMinimumSize(new Dimension(600, 600));
        setVisible(true);

        aShips[0][0] = 1;

        //Declare Buttons in Array:
        for (int x = 0; x < aButtons.length; x++)
            for (int y = 0; y < aButtons[x].length; y++) {
                aButtons[x][y] = new JButton();
                aButtons[x][y].setActionCommand(x + "," + y);
                aButtons[x][y].addActionListener(new Listener(gameSummary, gameField));
                aButtons[x][y].setIcon( mine );
            }
        //Set Description for First Row:
        for (int i = 1; i < aButtons[0].length; i++) {
            aButtons[0][i].setText(String.valueOf(i));
            aButtons[0][i].setEnabled(false);
        }

        //Set Description for First Cols:
        for (int i = 1; i < aButtons.length; i++) {
            charStart = 65;
            aButtons[i][0].setText(Character.toString((char) charStart + i - 1));
            aButtons[i][0].setEnabled(false);
        }

        aButtons[0][0].setEnabled(false);

        //Add Buttons to the Panel:
        for (JButton[] buttons : aButtons)
            for (JButton button : buttons)
                add(button);
    }


    public int[][] getaShips() {
        return aShips;
    }

    public void setaShips(int[][] aShips) {
        this.aShips = aShips;
    }

    public void placeShip(int x, int y) {
        aShips[x][y] = 1;
        aButtons[x+1][y+1].setEnabled( false );
    }

    public void changeButton(int x, int y, boolean status) {
        aButtons[x][y].setEnabled( status );
    }


    public void destroyShip(int x, int y) {
        aButtons[x][y].setEnabled(false);
    }
}
