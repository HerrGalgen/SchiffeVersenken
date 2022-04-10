package GameIO;

import Panels.GameField;
import Summaries.GameSummary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

/**
 * All GameIO.Listener for the Panels.GameField e.g. for clicking one Field.
 */

public class ButtonListener implements ActionListener {

    private GameSummary gameSummary;
    private GameField       gameField;
    private int             xCord       = 0;
    private int             yCord       = 0;
    private StringTokenizer tokenizer;
    private int             id;


    /**
     * @param gameSummary The Summaries.GameSummary
     * @param gameField The current Panels.GameField.
     * @param id The current PlayerID.
     * Start all other GameIO.
     */
    public ButtonListener(GameSummary gameSummary, GameField gameField, int id) {
        this.gameSummary = gameSummary;
        this.gameField = gameField;
        this.id = id;
    }

    /**
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed( ActionEvent e ) {

        if ( e.getActionCommand().equals( "next" ) ) {

            gameSummary.setClickCount( gameSummary.getClickCount() + 1 );
            System.out.println( "next" );
            gameField.removeNext();
            gameSummary.pauseGame(id);

            if ( gameSummary.getClickCount() == 2 )
                gameSummary.startGame();

        }else {

            tokenizer = new StringTokenizer( e.getActionCommand(), "," );
            xCord = Integer.parseInt( tokenizer.nextToken() ) - 1;
            yCord = Integer.parseInt( tokenizer.nextToken() ) - 1;

            if (gameSummary.getStatus().equals( "setShips" )) {

                System.out.println( xCord + " " + yCord + " ship set");
                gameField.getPlayground().placeShip( xCord, yCord );


            } else if ( gameSummary.getStatus().equals( "battle" ) ) {
                System.out.println( xCord + " " + yCord + " rocketed");

                //Test if ship was clicked:
                if ( gameField.getPlayground().getaShips()[xCord][yCord] == 1 ) {

                    gameField.getPlayground().getaShips()[xCord][yCord]++;
                    gameField.getPlayground().destroyShip( xCord, yCord);
                    gameField.getPlayground().setButtonIcon( xCord , yCord, "bombedBoat" );

                    System.out.println( "ship hitted" );

                    if(gameField.getPlayground().isWin())
                        System.out.println("WIN " + id);

                } else {
                    gameField.getPlayground().setButtonIcon( xCord, yCord, "mine" );
                    gameField.getPlayground().changeButton( xCord,yCord,false );
                    gameSummary.pauseGame(id);
                }
            }
        }
    }
}
