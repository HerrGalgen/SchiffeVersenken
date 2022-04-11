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

        //
        //Next-Button Clicked
        //
        if ( e.getActionCommand().equals( "next" ) ) {

            gameSummary.setClickCount( gameSummary.getClickCount() + 1 );
            System.out.println( "next" );
            gameField.removeNext();
            gameSummary.pauseGame(id);

            if ( gameSummary.getClickCount() == 2 )
                gameSummary.startGame();

            //
            //Playground Button clicked:
            //
        } else {

            StringTokenizer tokenizer = new StringTokenizer( e.getActionCommand(), "," );
            int yCord = Integer.parseInt( tokenizer.nextToken() ) - 1;
            int xCord = Integer.parseInt( tokenizer.nextToken() ) - 1;


            if ( gameSummary.getStatus().equals( "setShips" ) ) {

                if ( gameField.getShipSelector().isPlaceable() )
                    playground.placeShip( xCord, yCord, gameField.getShipSelector().getSelectedShipID() );


            } else if ( gameSummary.getStatus().equals( "battle" ) ) {

                System.out.println( xCord + " " + yCord + " rocketed");

                //Test if ship was clicked:
                if ( playground.getaShips()[xCord][yCord] == 1 ) {

                    destroyShip( xCord, yCord );

                    if ( gameField.getPlayground().isWin() )
                        System.out.println( "WIN " + id );

                } else { // no ship was hit:
                    playground.setButtonIcon( xCord, yCord, "mine" );
                    playground.changeButton( xCord, yCord, false );
                    gameSummary.pauseGame( id );
                }
            }
        }
    }

    private void destroyShip( int x, int y ) {

        playground.getaShips()[x][y]++;
        playground.destroyShip( x, y );
        playground.setButtonIcon( x, y, "bombedBoat" );

        System.out.println( "ship hitted" );
    }
}
