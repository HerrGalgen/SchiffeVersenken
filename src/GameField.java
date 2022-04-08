import javax.swing.*;
import java.awt.*;

/**
 * Creates a JPanel which includes an Playground and an Shipselector.
 */
public class GameField extends JPanel {

    private GridBagConstraints  cons            = new GridBagConstraints();
    private Playground          playground;
    private SelectShip          shipSelector;
    private JButton             bNext           = new JButton("NEXT");
    private Listener            listener;


    GameField( GameSummary gameSummary, int id ) {
        setLayout( new GridBagLayout() );
        setMinimumSize( new Dimension( gameSummary.getWidth(), gameSummary.getHeight() ) );
       // setVisible( true );
        listener = new Listener(gameSummary,this,id);

        bNext.setActionCommand( "next" );
        bNext.addActionListener( listener );

        playground = new Playground( this, gameSummary, id);
        shipSelector = new SelectShip( id );

        cons.gridx = 0;
        cons.gridy = 0;

        cons.weighty = 1;
        cons.weightx = 1;
        cons.fill = GridBagConstraints.BOTH;

        add( playground, cons );

        cons.gridy++;
        add( shipSelector, cons );

        cons.gridy++;
        add(bNext, cons);
    }

    public Playground getPlayground() {
        return playground;
    }

    public Listener getListener() {
        return listener;
    }

    public void removeNext() {
        remove( bNext );
    }
}
