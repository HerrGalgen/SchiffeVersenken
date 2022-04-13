package Panels;

import GameIO.ButtonListener;
import Summaries.GameSummary;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Creates a JPanel with only the buttons of the Game
 */

public class Playground extends JPanel {

    private final JButton[][] aButtons  = new JButton[11][11];
    private final GameSummary gameSummary;
    private final GameField   gameField;
    private       int[][]     aShips    = new int[aButtons.length - 1][aButtons[0].length - 1]; // 0 = default; 1 = ship; 2 = broken ship


    /**
     * @param gameField   current Panels.GameField.
     * @param gameSummary Summaries.GameSummary.
     */
    Playground( GameField gameField, GameSummary gameSummary, int id ) {
        setLayout( new GridLayout( aButtons.length, aButtons[0].length ) );
        setMinimumSize( new Dimension( 600, 600 ) );
        setVisible(true);

        this.gameSummary = gameSummary;
        this.gameField = gameField;

        ButtonListener listener = new ButtonListener(gameSummary, gameField, id);

        //Declare Buttons in Array:
        for (int y = 0; y < aButtons.length; y++)
            for (int x = 0; x< aButtons[y].length; x++) {
                aButtons[y][x] = new JButton();
                aButtons[y][x].setActionCommand(y + "," + x);
                aButtons[y][x].addActionListener(listener);
            }
        //Set Description for First Row:
        for (int i = 1; i < aButtons[0].length; i++) {
            aButtons[0][i].setText(String.valueOf(i));
            aButtons[0][i].setEnabled(false);
        }

        int charStart = 65;
        //Set Description for First Cols:
        for (int i = 1; i < aButtons.length; i++) {
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
        for(int y = 1; y < aButtons.length; y++)
            for(int x = 1; x < aButtons[y].length; x++)
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

    public void placeShip(int y, int x, int shipSize) {

        if( gameField.getShipSelector().isHorizontal()) {
            if ( x + shipSize < aShips.length + 1 ) {
                for (int i = 0; i < shipSize; i++) {
                    System.out.println( x + " " + y + " ship set" );
                    aShips[y][x] = 1;
                    aButtons[y + 1][x + 1].setEnabled( false );
                    x++;
                }
                gameField.getShipSelector().removeShipCount( shipSize );
            }
        } else {
            if ( (y + 1 - shipSize) >= 0 ) {
                for (int i = shipSize; i > 0; i--) {
                    System.out.println( x + " " + y  + " ship set" );
                    aShips[y][x] = 1;
                    aButtons[y + 1][x+ 1].setEnabled( false );
                    y--;
                }
                gameField.getShipSelector().removeShipCount( shipSize );
            }
        }
        markAllOwnShips();
    }

    public void changeButton(int y, int x, boolean status) {
        aButtons[y][x].setEnabled( status );
    }

    public void setButtonIcon(int y, int x, String iconType) {
        try {
            switch (iconType) {
                case "bombedBoat" -> aButtons[y][x].setIcon( new ImageIcon( ImageIO.read(Objects.requireNonNull(getClass().getResource("/pictures/bombedBoat.png"))) ) );
                case "ship"       -> aButtons[y][x].setIcon( new ImageIcon( ImageIO.read(Objects.requireNonNull(getClass().getResource("/pictures/ship.png"))) ) );
                case "mine"       -> aButtons[y][x].setIcon( new ImageIcon( ImageIO.read(Objects.requireNonNull(getClass().getResource("/pictures/mine.png"))) ) );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameSummary.pack();

    }

    public void markAllOwnShips() {

        for ( int x = 1; x < aButtons.length; x++ )
            for ( int y = 1; y < aButtons[x].length; y++ )
                if(aShips[y-1][x-1] == 1)
                    setButtonIcon( y,x,"ship" );

    }
}
