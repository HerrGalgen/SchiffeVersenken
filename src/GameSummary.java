import javax.swing.*;
import java.awt.*;

/**
 * The whole GameSummary. It creates the two Players and switches between them.
 */

public class GameSummary extends JFrame {

    GameField player1 = new GameField( this, 1 );
    GameField player2 = new GameField( this, 2 );

    GameSummary() {
        setLayout( new CardLayout() );
        add( player1 );
        add( player2 );
    }

    public void init() {
        setMinimumSize( new Dimension( 550, 600 ) );
        setVisible( true );

        player2.setVisible( false );
        player1.setVisible( true );

        player1.setBackground( Color.GREEN );
        player2.setBackground( Color.RED );
    }

    public void changeView() {
        player1.setVisible( !player1.isVisible() );
        player2.setVisible( !player2.isVisible() );
    }
}
