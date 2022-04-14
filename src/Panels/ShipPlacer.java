package Panels;

import GameIO.ShipPlacerListener;
import Summaries.GameSummary;

import javax.swing.*;
import java.awt.*;

/**
 * The Panel show on the Start your own Ships.
 * In the game, it shows the ships from the enemy.
 */

public class ShipPlacer extends JPanel {

    private final JRadioButton  bFive  = new JRadioButton( "0x Schlachtschiff (5er)" );
    private final JRadioButton  bFour  = new JRadioButton( "0x Kreuzer (4er)" );
    private final JRadioButton  bThree = new JRadioButton( "0x Zerstörer (3er)" );
    private final JRadioButton  bTwo   = new JRadioButton( "0x U-Boote (2er)" );
    private final JToggleButton toggle = new JToggleButton( "Horizontal" );

    private int countFive  = 1;
    private int countFour  = 2;
    private int countThree = 3;
    private int countTwo = 4;

    private boolean horizontal = true;

    ButtonGroup buttonGroup = new ButtonGroup();

    ShipPlacer( int id, GameSummary gameSummary ) {
        setMinimumSize( new Dimension( gameSummary.getWidth(), gameSummary.getHeight() ) );

        setBackground( (id == 1) ? Color.PINK : Color.LIGHT_GRAY );
        bFive.setBackground( (id == 1) ? Color.PINK : Color.LIGHT_GRAY );
        bFour.setBackground( (id == 1) ? Color.PINK : Color.LIGHT_GRAY );
        bThree.setBackground( (id == 1) ? Color.PINK : Color.LIGHT_GRAY );
        bTwo.setBackground( (id == 1) ? Color.PINK : Color.LIGHT_GRAY );

        ShipPlacerListener listener = new ShipPlacerListener( this, gameSummary, id );

        bTwo.setSelected(true);

        toggle.addActionListener(listener);
        toggle.setActionCommand("toggle");

        JButton bNext = new JButton("NEXT");
        bNext.setMinimumSize(new Dimension(100,100));
        bNext.setActionCommand("next");
        bNext.addActionListener(listener);

        setShipCount();

        buttonGroup.add(bFive);
        buttonGroup.add(bFour);
        buttonGroup.add(bThree);
        buttonGroup.add(bTwo);

        add(bFive);
        add(bFour);
        add(bThree);
        add(bTwo);
        add(toggle);
        add(bNext);
    }

    public void setShipCount() {
        bFive.setText(countFive + bFive.getText().substring(1));
        bFour.setText(countFour + bFour.getText().substring(1));
        bThree.setText(countThree + bThree.getText().substring(1));
        bTwo.setText(countTwo + bTwo.getText().substring(1));
    }

    public int getSelectedShipID() {
        int shipID = 0;

        if (bFive.isSelected())
            shipID = 5;

        if (bFour.isSelected())
            shipID = 4;

        if (bThree.isSelected())
            shipID = 3;

        if (bTwo.isSelected())
            shipID = 2;

        return shipID;
    }

    public void removeShipCount(int shipID) {

        switch(shipID) {
            case 5 -> {
                countFive--;
                if(countFive == 0)
                    bFive.setEnabled(false);
            }
            case 4 -> {
                countFour--;
                if(countFour==0)
                    bFour.setEnabled(false);
            }
            case 3 -> {
                countThree--;
                if(countThree == 0)
                    bThree.setEnabled(false);
            }
            case 2 -> {
                countTwo--;
                if(countTwo == 0)
                    bTwo.setEnabled(false);
            }
            default -> throw new IllegalStateException("Unexpected value: " + shipID);
        }

        setShipCount();
    }

    public boolean isPlaceable() {
        switch(getSelectedShipID()) {
            case 5 -> {
                return countFive != 0;
            }
            case 4 -> {
                return countFour != 0;
            }
            case 3 -> {
                return countThree != 0;
            }
            case 2 -> {
                return countTwo != 0;
            }
        }
        return false;
    }

    public void toggleSwitch() {
        if ( toggle.getText().equals( "Horizontal" ) )
            toggle.setText( "Vertikal" );
        else
            toggle.setText( "Horizontal" );

        setHorizontal( !isHorizontal() );

    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal( boolean horizontal ) {
        this.horizontal = horizontal;
    }

    public int[] getPlacedShips() {
        int[] ships = new int[4];

        ships[0] = 4 - countTwo;
        ships[1] = 3 - countThree;
        ships[2] = 2 - countFour;
        ships[3] = 1 - countFive;

        return ships;
    }
}