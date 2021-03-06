package Summaries;

import Panels.Playground;
import Panels.ShipPlacer;
import Panels.ShipShower;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a JPanel which includes an Panels.Playground and an Shipselector.
 */
public class PlayerSummary extends JPanel {

    private final Playground         playground;
    private final ShipPlacer         shipPlacer;
    private final ShipShower         shipShower;
    private final GridBagConstraints cons = new GridBagConstraints();


    /**
     * @param gameSummary The Summaries.GameSummary
     * @param id          The current PlayerID.
     */
    public PlayerSummary( GameSummary gameSummary, int id ) {
        System.out.println("---[ Spieler " + id + " erstellt ]---");
        setLayout( new GridBagLayout() );
        setMinimumSize( new Dimension( gameSummary.getWidth(), gameSummary.getHeight() ) );

        playground = new Playground( this, gameSummary, id );
        shipPlacer = new ShipPlacer( id, gameSummary );
        shipShower = new ShipShower( gameSummary, id );

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
        playground.removeBlocked();
    }
}
