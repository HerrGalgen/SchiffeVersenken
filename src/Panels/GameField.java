package Panels;

import GameIO.*;
import Summaries.*;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a JPanel which includes an Panels.Playground and an Shipselector.
 */
public class GameField extends JPanel {

    private GridBagConstraints  cons            = new GridBagConstraints();
    private Playground playground;
    private SelectShip shipSelector;
    private JButton             bNext           = new JButton("NEXT");
    private ButtonListener listener;


    /**
     * @param gameSummary The Summaries.GameSummary
     * @param id The current PlayerID.
     */
    public GameField(GameSummary gameSummary, int id) {
        setLayout( new GridBagLayout() );
        setMinimumSize( new Dimension( gameSummary.getWidth(), gameSummary.getHeight() ) );
       // setVisible( true );
        listener = new ButtonListener(gameSummary,this,id);

        bNext.setActionCommand( "next" );
        bNext.addActionListener( listener );

        playground = new Playground( this, gameSummary, id);
        shipSelector = new SelectShip( id );

        cons.gridx = 0;
        cons.gridy = 0;

        cons.weighty = 1;
        cons.weightx = 1;
        cons.fill = GridBagConstraints.BOTH;

        add( playground, cons );

        cons.gridy++;
        add( shipSelector, cons );

        cons.gridy++;
        add(bNext, cons);
    }

    /**
     * @return current Panels.Playground.
     */
    public Playground getPlayground() {
        return playground;
    }

    /**
     * @return current GameIO.Listener.
     */
    public ButtonListener getListener() {
        return listener;
    }

    /**
     * Removes the Next button from the player.
     */
    public void removeNext() {
        remove( bNext );
    }
}
