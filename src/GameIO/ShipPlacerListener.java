package GameIO;

import Panels.ShipPlacer;
import Summaries.GameSummary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShipPlacerListener implements ActionListener {

    ShipPlacer  shipPlacer;
    GameSummary gameSummary;
    int         id;

    public ShipPlacerListener( ShipPlacer shipPlacer, GameSummary gameSummary, int id ) {
        this.shipPlacer = shipPlacer;
        this.gameSummary = gameSummary;
        this.id = id;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("toggle")){
            shipPlacer.toggleSwitch();
        }
        else if(e.getActionCommand().equals("next")) {
            gameSummary.setClickCount( gameSummary.getClickCount() + 1 );
            System.out.println( "--[ Spieler " + ((id==1) ? 2 : 1) + " ist am Zug ]--" );
            gameSummary.pauseGame( id );
            gameSummary.getCurrentPlayer( id ).prepareGame();

            if ( gameSummary.getClickCount() == 2 )
                gameSummary.startGame();
        }

    }
}
