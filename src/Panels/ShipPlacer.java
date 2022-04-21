package Panels;

import GameIO.ShipPlacerListener;
import Summaries.GameSummary;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

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

    GameSummary gameSummary;

    ButtonGroup buttonGroup = new ButtonGroup();

    public ShipPlacer( int id, GameSummary gameSummary ) {
        System.out.println("---[ ShipPlayer " + id + " erstellt ]---");
        setMinimumSize( new Dimension( gameSummary.getWidth(), gameSummary.getHeight() ) );
        setBackground( (id == 1) ?
                Color.decode(gameSummary.getProperty("p1Color")) :
                Color.decode(gameSummary.getProperty("p2Color")) );

        this.gameSummary = gameSummary;

        initButtons( id );

        ShipPlacerListener listener = new ShipPlacerListener( this, gameSummary, id );

        bTwo.setSelected( true );

        //Settings for KeyListener:
        bTwo.setFocusable(false);
        bThree.setFocusable(false);
        bFour.setFocusable(false);
        bFive.setFocusable(false);

        toggle.addActionListener( listener );
        toggle.setActionCommand( "toggle" );
        toggle.setFocusable( false );

        JButton bNext = new JButton( "NEXT" );
        bNext.setMinimumSize( new Dimension( 100, 100 ) );
        bNext.setActionCommand( "next" );
        bNext.addActionListener( listener );
        bNext.setBackground( Color.RED );
        bNext.setForeground( Color.WHITE );
        bNext.setFocusable( false );

        setShipCount();

        buttonGroup.add( bFive );
        buttonGroup.add( bFour );
        buttonGroup.add( bThree );
        buttonGroup.add( bTwo );

        add( bFive );
        add( bFour );
        add( bThree );
        add( bTwo );
        add( toggle );
        add( bNext );
    }

    private void initButtons( int id ) {

        Font rbFont = new Font( "Dialog", Font.BOLD, 15 );

        bFive.setBackground( (id == 1) ?
                Color.decode(gameSummary.getProperty("p1Color")) :
                Color.decode(gameSummary.getProperty("p2Color")) );

        bFour.setBackground( (id == 1) ?
                Color.decode(gameSummary.getProperty("p1Color")) :
                Color.decode(gameSummary.getProperty("p2Color")) );

        bThree.setBackground( (id == 1) ?
                Color.decode(gameSummary.getProperty("p1Color")) :
                Color.decode(gameSummary.getProperty("p2Color")) );

        bTwo.setBackground( (id == 1) ?
                Color.decode(gameSummary.getProperty("p1Color")) :
                Color.decode(gameSummary.getProperty("p2Color")) );

        bFive.setFont( rbFont );
        bFour.setFont( rbFont );
        bThree.setFont( rbFont );
        bTwo.setFont( rbFont );
    }

    public void setShipCount() {
        bFive.setText( countFive + bFive.getText().substring( 1 ) );
        bFour.setText( countFour + bFour.getText().substring( 1 ) );
        bThree.setText( countThree + bThree.getText().substring( 1 ) );
        bTwo.setText( countTwo + bTwo.getText().substring( 1 ) );
    }

    public int getSelectedShipSize() {
        int shipSize = 0;

        if ( bFive.isSelected() )
            shipSize = 5;

        if ( bFour.isSelected() )
            shipSize = 4;

        if ( bThree.isSelected() )
            shipSize = 3;

        if (bTwo.isSelected())
            shipSize = 2;

        return shipSize;
    }

    public void removeShipCount(int shipID) {

        switch (shipID) {
            case 5:
                countFive--;
                if (countFive == 0)
                    bFive.setEnabled(false);
            break;
            case 4:
                countFour--;
                if (countFour == 0)
                    bFour.setEnabled(false);
            break;
            case 3:
                countThree--;
                if (countThree == 0)
                    bThree.setEnabled(false);
            break;
            case 2:
                countTwo--;
                if (countTwo == 0)
                    bTwo.setEnabled(false);
            break;
            default: throw new IllegalStateException("Unexpected value: " + shipID);
        }

        setShipCount();
    }

    public boolean isAvailable() {
         switch (getSelectedShipSize()) {
            case 5: return countFive != 0;
            case 4: return countFour != 0;
            case 3: return countThree != 0;
            case 2: return countTwo != 0;
            default: return false;
        }
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

    public void checkShipSizeCount(int shipSize) {
       switch(shipSize) {
           case 2: if(countTwo == 0) bThree.setSelected(true); break;
           case 3: if(countThree == 0) bFour.setSelected(true); break;
           case 4: if(countFour == 0) bFive.setSelected(true); break;
       }
    }


}