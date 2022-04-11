package Panels;

import Panels.*;
import Summaries.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * Creates a JPanel with only the buttons of the Game
 */

public class Playground extends JPanel{

    private int         charStart   = 0;
    private JButton[][] aButtons    = new JButton[11][11];
    private int[][]     aShips      = new int[aButtons.length-1][aButtons[0].length-1]; // 0 = default; 1 = ship; 2 = broken ship
    private GameSummary gameSummary;
    private GameField gameField;


    /**
     * @param gameField current Panels.GameField.
     * @param gameSummary Summaries.GameSummary.
     * @param id PlayerID.
     */
    Playground(GameField gameField, GameSummary gameSummary, int id) {
        setLayout(new GridLayout(aButtons.length, aButtons[0].length));
        setMinimumSize(new Dimension(600, 600));
        setVisible(true);

        this.gameSummary = gameSummary;
        this.gameField = gameField;

        //Declare Buttons in Array:
        for (int x = 0; x < aButtons.length; x++)
            for (int y = 0; y < aButtons[x].length; y++) {
                aButtons[x][y] = new JButton();
                aButtons[x][y].setActionCommand(x + "," + y);
                aButtons[x][y].addActionListener(gameField.getListener());
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

    //
    //Other useful function:
    //
    
    public void activateField() {
        for(int x = 1; x < aButtons.length; x++)
            for(int y = 1; y < aButtons[x].length; y++)
                aButtons[x][y].setEnabled( true );
    }

    public boolean isWin() {
        for(int[] rows : aShips)
            if(contains( rows,1 ))
                return false;

        return true;
    }

    public boolean contains(int[] arr, int key) {
        return Arrays.stream(arr).anyMatch( i -> i == key);
    }

    //
    //Getter - Setter:
    //
    
    public int[][] getaShips() {
        return aShips;
    }

    public void setaShips(int[][] aShips) {
        this.aShips = aShips;
    }

    public void placeShip(int x, int y, int shipSize) {

        if(x+shipSize < aShips.length+1) {
            for (int i = 0; i < shipSize; i++) {
                System.out.println(x + i + " " + y + " ship set");
                aShips[y][x] = 1;
                aButtons[y + 1][x + 1].setEnabled(false);
                x++;
            }
            gameField.getShipSelector().removeShipCount(shipSize);
        }
        markAllOwnShips();
    }

    public void changeButton(int x, int y, boolean status) {
        aButtons[x+1][y+1].setEnabled( status );
    }


    public void destroyShip(int x, int y) {
        aButtons[x+1][y+1].setEnabled(false);
    }

    public void setButtonIcon(int x, int y, String iconType) {
        try {
            switch (iconType) {
                case "bombedBoat" -> aButtons[x + 1][y + 1].setIcon( new ImageIcon( ImageIO.read( getClass().getResource( "/pictures/bombedBoat.png" ) ) ) );
                case "ship"       -> aButtons[x + 1][y + 1].setIcon( new ImageIcon( ImageIO.read( getClass().getResource( "/pictures/ship.png" ) ) ) );
                case "mine"       -> aButtons[x + 1][y + 1].setIcon( new ImageIcon( ImageIO.read( getClass().getResource( "/pictures/mine.png" ) ) ) );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameSummary.pack();

    }

    public void markAllOwnShips() {

        for ( int x = 1; x < aButtons.length; x++ )
            for ( int y = 1; y < aButtons[x].length; y++ )
                if(aShips[x-1][y-1] == 1)
                    setButtonIcon( x-1,y-1,"ship" );

    }
}
