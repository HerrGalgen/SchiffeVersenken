package GameIO;

import Summaries.PlayerSummary;
import Panels.Playground;
import Summaries.GameSummary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

/**
 * All GameIO.Listener for the Summaries.PlayerSummary e.g. for clicking one Field.
 */

public class ButtonListener implements ActionListener {

    private final GameSummary   gameSummary;
    private final PlayerSummary playerSummary;
    private final int           id;
    private       Playground  playground;
    private       int         shipID = 0;


    /**
     * @param gameSummary The Summaries.GameSummary
     * @param playerSummary   The current Summaries.PlayerSummary.
     * @param id          The current PlayerID.
     */
    public ButtonListener( GameSummary gameSummary, PlayerSummary playerSummary, int id ) {
        this.gameSummary = gameSummary;
        this.playerSummary = playerSummary;
        this.id = id;
    }

    /**
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed( ActionEvent e ) {

        playground = playerSummary.getPlayground();

        StringTokenizer tokenizer = new StringTokenizer( e.getActionCommand(), "," );
        int yCord = Integer.parseInt( tokenizer.nextToken() ) - 1;
        int xCord = Integer.parseInt( tokenizer.nextToken() ) - 1;


        if ( gameSummary.getStatus().equals( "setShips" ) ) {

            if ( playerSummary.getShipPlacer().isAvailable() && playground.isPlaceable( yCord, xCord, playerSummary.getShipPlacer().getSelectedShipSize() ) ) {
                shipID++;
                playground.placeShip( yCord, xCord, playerSummary.getShipPlacer().getSelectedShipSize(), shipID );
                playerSummary.getShipPlacer().checkShipSizeCount(playerSummary.getShipPlacer().getSelectedShipSize());
            }


        } else if ( gameSummary.getStatus().equals( "battle" ) ) {

            System.out.println( "--[ Spieler " + id + " - y:" + yCord + " x:" + xCord + " beschossen ]--");

            //Test if ship was clicked:
            if ( playground.getaShips()[yCord][xCord] != 0 ) {

                destroyShip( yCord, xCord );

                if ( playerSummary.getPlayground().isWin() ) {
                    System.out.println( "-----[ Spieler  " + id + " hat gewonnen! ]-----" );
                    gameSummary.setWin( id );
                }

            } else { // no ship was hit:
                playground.setButtonIcon( ++yCord, ++xCord, "mine" );
                playground.changeButton( yCord, xCord, false );
                gameSummary.pauseGame( id );
            }
        }
    }

    private void destroyShip( int y, int x ) {
        shipID = playground.getIDtoCord( y, x );

        playground.getaShips()[y][x] = -shipID;
        playground.changeButton( y + 1, x + 1, false );
        playground.setButtonIcon( y + 1, x + 1, "bombedBoat" );

        //Complete ship was Destroyed
        if ( !playground.shipInArray( playground.getaShips(), shipID ) ) {
            playerSummary.getShipShower().removeDestroyedShip( playground.getSizeToID( shipID ) );
            playerSummary.getPlayground().markDestroyedShip(-shipID);
        }
    }


}
