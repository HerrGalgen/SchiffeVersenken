package Panels;

import Summaries.GameSummary;

import javax.swing.*;
import java.awt.*;

public class ShipShower extends JPanel {

    int[] shipsLeft = new int[4];

    GameSummary gameSummary;

    JLabel lFive  = new JLabel();
    JLabel lFour  = new JLabel();
    JLabel lThree = new JLabel();
    JLabel lTwo   = new JLabel();


    ShipShower( GameSummary gameSummary, int id ) {
        this.gameSummary = gameSummary;
        setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

        setMinimumSize( new Dimension( gameSummary.getWidth(), gameSummary.getHeight() ) );

        setBackground( (id == 1) ? GameSummary.getColorP1() : GameSummary.getColorP2() );

        lTwo.setAlignmentX( Component.CENTER_ALIGNMENT );
        lThree.setAlignmentX( Component.CENTER_ALIGNMENT );
        lFour.setAlignmentX( Component.CENTER_ALIGNMENT );
        lFive.setAlignmentX( Component.CENTER_ALIGNMENT );

        lTwo.setFont( new Font( "Dialog", Font.PLAIN, 25 ) );
        lThree.setFont( new Font( "Dialog", Font.PLAIN, 25 ) );
        lFour.setFont( new Font( "Dialog", Font.PLAIN, 25 ) );
        lFive.setFont( new Font( "Dialog", Font.PLAIN, 25 ) );

        add( lTwo );
        add( lThree );
        add( lFour );
        add( lFive );
    }

    public void setShipsLeft( int[] shipsLeft ) {
        this.shipsLeft = shipsLeft;
        lTwo.setText( "2er Schiffe: " + shipsLeft[0] );
        lThree.setText( "3er Schiffe: " + shipsLeft[1] );
        lFour.setText( "4er Schiffe: " + shipsLeft[2] );
        lFive.setText( "5er Schiffe: " + shipsLeft[3] );
    }

    public void removeDestroyedShip( int shipSize ) {
        switch (shipSize) {
            case 2 -> shipsLeft[0]--;
            case 3 -> shipsLeft[1]--;
            case 4 -> shipsLeft[2]--;
            case 5 -> shipsLeft[3]--;
        }

        System.out.println( "Ship with size " + shipSize + "removed." );

        setShipsLeft( shipsLeft );

    }

    public int getPlacedShips() {
        int shipsleft = 0;

        for (int shipCount : shipsLeft) {
            shipsleft += shipCount;
        }

        return shipsleft;
    }
}
