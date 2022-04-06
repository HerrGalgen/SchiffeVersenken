import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

/**
 * All Listener for the GameField e.g. for clicking one Field.
 */

public class Listener implements ActionListener {

    GameSummary gameSummary;
    GameField   gameField;
    int xCord = 0;
    int yCord = 0;
    StringTokenizer tokenizer;

    Listener( GameSummary gameFrame, GameField gameField ) {
        this.gameSummary = gameFrame;
        this.gameField = gameField;
    }

    @Override
    public void actionPerformed( ActionEvent e ) {

        if ( e.getActionCommand().equals( "next" ) ) {
            gameSummary.setClickCount( gameSummary.getClickCount()+1 );
            System.out.println( "next" );
            gameField.removeNext();
            gameSummary.changeView();
            if(gameSummary.getClickCount()==2)
                gameSummary.startGame();

        } else {

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
                    gameField.getPlayground().destroyShip( xCord + 1, yCord + 1 );
                    System.out.println( "ship hitted" );

                } else {
                    gameField.getPlayground().changeButton( xCord+1,yCord+1,false );
                    gameSummary.changeView();
                }
            }
        }
    }
}
