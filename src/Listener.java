import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * All Listener for the GameField e.g. for clicking one Field.
 */

public class Listener implements ActionListener {

    GameSummary gameFrame;
    GameField   gameField;

    Listener( GameSummary gameFrame, GameField gameField ) {
        this.gameFrame = gameFrame;
        this.gameField = gameField;
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        System.out.println( e.getActionCommand() );

        if ( gameField.getId() == 1 )
            System.out.println( "1" );
        else
            System.out.println( "2" );

        gameFrame.changeView();
    }
}
