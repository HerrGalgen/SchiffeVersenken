package GameIO;

import Panels.GameField;
import Panels.Playground;
import Summaries.GameSummary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

/**
 * All GameIO.Listener for the Panels.GameField e.g. for clicking one Field.
 */

public class ButtonListener implements ActionListener {

    private final GameSummary gameSummary;
    private final GameField   gameField;
    private final int         id;
    private       Playground  playground;
    private       int         shipID = 0;


    /**
     * @param gameSummary The Summaries.GameSummary
     * @param gameField   The current Panels.GameField.
     * @param id          The current PlayerID.
     */
    public ButtonListener( GameSummary gameSummary, GameField gameField, int id ) {
        this.gameSummary = gameSummary;
        this.gameField = gameField;
        this.id = id;
    }

    /**
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed( ActionEvent e ) {

        playground = gameField.getPlayground();

        StringTokenizer tokenizer = new StringTokenizer( e.getActionCommand(), "," );
        int yCord = Integer.parseInt( tokenizer.nextToken() ) - 1;
        int xCord = Integer.parseInt( tokenizer.nextToken() ) - 1;


        if ( gameSummary.getStatus().equals( "setShips" ) ) {

            if ( gameField.getShipPlacer().isAvailable() && playground.isPlaceable( yCord, xCord, gameField.getShipPlacer().getSelectedShipSize() ) ) {
                shipID++;
                playground.placeShip( yCord, xCord, gameField.getShipPlacer().getSelectedShipSize(), shipID );
            }


        } else if ( gameSummary.getStatus().equals( "battle" ) ) {

            System.out.println( yCord + " " + xCord + " rocketed");

            //Test if ship was clicked:
            if ( playground.getaShips()[yCord][xCord] != 0 ) {

                destroyShip( yCord, xCord );

                if ( gameField.getPlayground().isWin() )
                    System.out.println( "WIN " + id );

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
            gameField.getShipShower().removeDestroyedShip( playground.getSizeToID( shipID ) );
            gameField.getPlayground().markDestroyedShip(-shipID);
        }

        System.out.println( "ship hitted" );
    }


}
