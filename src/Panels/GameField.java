package Panels;

import Summaries.GameSummary;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a JPanel which includes an Panels.Playground and an Shipselector.
 */
public class GameField extends JPanel {

    private final Playground         playground;
    private final ShipPlacer         shipPlacer;
    private final ShipShower         shipShower;
    private final GridBagConstraints cons = new GridBagConstraints();


    /**
     * @param gameSummary The Summaries.GameSummary
     * @param id          The current PlayerID.
     */
    public GameField( GameSummary gameSummary, int id ) {
        setLayout( new GridBagLayout() );
        setMinimumSize( new Dimension( gameSummary.getWidth(), gameSummary.getHeight() ) );

        playground = new Playground( this, gameSummary, id );
        shipPlacer = new ShipPlacer( id, gameSummary );
        shipShower = new ShipShower( gameSummary );

        cons.gridx = 0;
        cons.gridy = 0;

        cons.weighty = 1;
        cons.weightx = 1;
        cons.fill = GridBagConstraints.BOTH;

        add( playground, cons );

        cons.gridy++;
        add( shipPlacer, cons );
    }

    /**
     * @return current Panels.Playground.
     */
    public Playground getPlayground() {
        return playground;
    }

    public ShipPlacer getShipPlacer() {
        return shipPlacer;
    }

    public ShipShower getShipShower() {
        return shipShower;
    }

    public void prepareGame() {
        remove( shipPlacer );
        add( shipShower, cons );
    }
}
