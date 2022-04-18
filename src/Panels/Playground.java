package Panels;

import GameIO.Actions;
import GameIO.ButtonListener;
import Summaries.GameSummary;
import Summaries.PlayerSummary;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Creates a JPanel with only the buttons of the Game
 */

public class Playground extends JPanel {

    private final JButton[][]               aButtons   = new JButton[11][11];
    private final GameSummary   gameSummary;
    private final PlayerSummary playerSummary;
    private       int[][]       aShips     = new int[aButtons.length - 1][aButtons[0].length - 1];
    private       HashMap<Integer, Integer> shipIDSize = new HashMap<>();


    /**
     * @param playerSummary   current Summaries.PlayerSummary.
     * @param gameSummary Summaries.GameSummary.
     */
    public Playground( PlayerSummary playerSummary, GameSummary gameSummary, int id ) {
        System.out.println("---[ Playground " + id + " erstellt ]---");

        setLayout( new GridLayout( aButtons.length, aButtons[0].length ) );
        setMinimumSize( new Dimension( 600, 600 ) );
        setVisible( true );

        this.gameSummary = gameSummary;
        this.playerSummary = playerSummary;

        ButtonListener listener = new ButtonListener(gameSummary, playerSummary, id);

        //Declare Buttons in Array:
        for (int y = 0; y < aButtons.length; y++)
            for (int x = 0; x< aButtons[y].length; x++) {
                aButtons[y][x] = new JButton();
                aButtons[y][x].setActionCommand(y + "," + x);
                aButtons[y][x].addActionListener(listener);
                aButtons[y][x].setFocusable( false );
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
        for (int y = 1; y < aButtons.length; y++)
            for (int x = 1; x < aButtons[y].length; x++)
                aButtons[x][y].setEnabled( true );
    }

    public boolean isWin() {
        return playerSummary.getShipShower().getPlacedShips() == 0;
    }

    public boolean shipInArray( int[][] array, int id ) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                if ( anInt == id ) return true;
            }
        }

        return false;
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

    public void placeShip( int y, int x, int shipSize, int shipID ) {

        if ( playerSummary.getShipPlacer().isHorizontal() ) {
            for (int i = 0; i < shipSize; i++) {
                System.out.println("--[ y:" + y + " x:" + x + " - Schiff gesetzt ]--" );
                aShips[y][x] = shipID;
                aButtons[y + 1][x + 1].setEnabled( false );
                shipIDSize.put( shipID, shipSize );// ID = Size
                x++;
            }
        } else {
            for (int i = shipSize; i > 0; i--) {
                System.out.println("--[ y:" + y + " x:" + x + " - Schiff gesetzt ]--" );
                aShips[y][x] = shipID;
                aButtons[y + 1][x + 1].setEnabled( false );
                shipIDSize.put( shipID, shipSize ); // ID = Size
                y--;
            }
        }
        playerSummary.getShipPlacer().removeShipCount( shipSize );
        markAllOwnShips();

    }

    public void changeButton(int y, int x, boolean status) {
        aButtons[y][x].setEnabled( status );
    }

    public void setButtonIcon(int y, int x, String iconType) {
        try {
            switch (iconType) {
                case "bombedBoat" -> aButtons[y][x].setIcon( new ImageIcon( ImageIO.read( Objects.requireNonNull( getClass().getResource( "/pictures/bombedBoat.png" ) ) ) ) );
                case "ship" -> aButtons[y][x].setIcon( new ImageIcon( ImageIO.read( Objects.requireNonNull( getClass().getResource( "/pictures/ship.png" ) ) ) ) );
                case "mine" -> aButtons[y][x].setIcon( new ImageIcon( ImageIO.read( Objects.requireNonNull( getClass().getResource( "/pictures/mine.png" ) ) ) ) );
                case "block" -> aButtons[y][x].setIcon( new ImageIcon( ImageIO.read( Objects.requireNonNull( getClass().getResource( "/pictures/block.png" ) ) ) ) );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameSummary.pack();

    }

    public void markAllOwnShips() {

        for (int x = 1; x < aButtons.length; x++)
            for (int y = 1; y < aButtons[x].length; y++)
                if ( aShips[y - 1][x - 1] != 0 )
                    setButtonIcon( y, x, "ship" );

    }

    public boolean isPlaceable( int y, int x, int shipSize ) {

        boolean placeable;

        //ship is in bounds:
        if ( playerSummary.getShipPlacer().isHorizontal() )
            placeable = x + shipSize < aShips.length + 1;
        else
            placeable = y + 1 - shipSize >= 0;

        if ( !placeable )
            return false;

        return isValidPlace( y, x, shipSize );

    }

    public boolean isValidPlace( int y, int x, int shipSize ) {
        int startPosX;
        int startPosY;
        int endPosX;
        int endPosY;

        if ( playerSummary.getShipPlacer().isHorizontal() ) {
            startPosX = (x <= 0) ? x : x - 1;
            startPosY = (y <= 0) ? y : y - 1;
            endPosX = (x + shipSize + 1 < aShips[y].length + 1) ? x + shipSize : x + shipSize - 1;
            endPosY = (y + 1 < aShips.length + 1) ? y + 1 : y;
        } else {
            startPosX = (x <= 0) ? x : x - 1;
            startPosY = (y - shipSize >= 0) ? y - shipSize : y - 1;
            endPosX = (x < aShips[y].length) ? x + 1 : x - 1;
            endPosY = (y - shipSize < aShips.length + 1) ? y + 1 : y - 1;
        }


        if ( endPosY > 9 )
            endPosY = 9;

        if ( endPosX > 9 )
            endPosX = 9;

        // See how many are alive
        for (int xNum = startPosX; xNum <= endPosX; xNum++)
            for (int yNum = startPosY; yNum <= endPosY; yNum++) {
                if ( aShips[yNum][xNum] != 0 )
                    return false;
            }

        for (int xNum = startPosX; xNum <= endPosX; xNum++)
            for (int yNum = startPosY; yNum <= endPosY; yNum++)
                setButtonIcon( yNum + 1, xNum + 1, "block" );

        return true;
    }

    public int getSizeToID( int shipID ) {
        return shipIDSize.get( shipID );
    }

    public int getIDtoCord( int y, int x ) {
        return aShips[y][x];
    }

    public HashMap<Integer, Integer> getShipIDSize() {
        return shipIDSize;
    }

    public void setShipIDSize( HashMap<Integer, Integer> shipIDSize ) {
        this.shipIDSize = shipIDSize;
    }

    public void removeBlocked() {
        for(JButton[] buttons : aButtons)
            for(JButton button : buttons)
                button.setIcon( null );

        markAllOwnShips();
    }

    public void markDestroyedShip( int desShipID ) {

        for (int x = 0; x < aShips.length; x++)
            for (int y = 0; y < aShips[x].length; y++)
                if ( aShips[y][x] == desShipID )
                    aButtons[y+1][x+1].setBackground( Color.RED );


    }
}
