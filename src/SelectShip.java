import javax.swing.*;
import java.awt.*;

/**
 * The Panel show on the Start your own Ships.
 * In the game, it shows the ships from the enemy.
 */

public class SelectShip extends JPanel {

    SelectShip( int id ) {
        setMinimumSize( new Dimension( 550, 50 ) );
        add( new JLabel( String.valueOf( id ) ) );
    }

}
