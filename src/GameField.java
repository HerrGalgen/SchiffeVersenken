import javax.swing.*;
import java.awt.*;

/**
 * Creates a JPanel which includes an Playground and an Shipselector.
 */

public class GameField extends JPanel {

    private int                id   = 0;
    private GridBagConstraints cons = new GridBagConstraints();


    GameField( GameSummary gameFrame, int id ) {
        setLayout( new GridBagLayout() );
        setMinimumSize( new Dimension( gameFrame.getWidth(), gameFrame.getHeight() ) );
        setVisible( true );
        this.id = id;

        SelectShip shipSelector = new SelectShip( id );
        Playground playground = new Playground( this, gameFrame, id );

        cons.gridx = 0;
        cons.gridy = 0;

        cons.weighty = 1;
        cons.fill = GridBagConstraints.BOTH;

        add( playground, cons );

        cons.gridy++;
        cons.gridx = 0;
        add( shipSelector, cons );
    }

    public int getId() {
        return id;
    }
}
