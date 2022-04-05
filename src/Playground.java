import javax.swing.*;
import java.awt.*;

/**
 * Creates a JPanel with only the buttons of the Game
 */

public class Playground extends JPanel {

    Playground( GameField gameField, GameSummary gameSummary, int id ) {
        JButton[][] aButtons = new JButton[11][11];
        setLayout( new GridLayout( aButtons.length, aButtons[0].length ) );
        setMinimumSize( new Dimension( 550, 550 ) );
        setVisible( true );

        //Declare Buttons in Array:
        for (int x = 0; x < aButtons.length; x++)
            for (int y = 0; y < aButtons[x].length; y++) {
                aButtons[x][y] = new JButton();
                aButtons[x][y].setActionCommand( x + "," + y );
                aButtons[x][y].addActionListener( new Listener( gameSummary, gameField ) );
            }
        //Set Description for First Row:
        for (int i = 1; i < aButtons[0].length; i++) {
            aButtons[0][i].setText( String.valueOf( i ) );
            aButtons[0][i].setEnabled( false );
        }

        //Set Description for First Cols:
        for (int i = 1; i < aButtons.length; i++) {
            int charStart = 65;
            aButtons[i][0].setText( Character.toString( (char) charStart + i - 1 ) );
            aButtons[i][0].setEnabled( false );
        }

        aButtons[0][0].setEnabled( false );

        //Add Buttons to the Panel:
        for (JButton[] buttons : aButtons)
            for (JButton button : buttons)
                add( button );
    }

}
