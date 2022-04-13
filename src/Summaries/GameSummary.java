package Summaries;

import GameIO.*;
import Panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * The whole Summaries.GameSummary. It creates the two Players and switches between them.
 */
public class GameSummary extends JFrame {

    private final GameField player1;
    private final GameField player2;
    private final PausePanel pausePanel;
    private       int        pausedPlayer = 0;
    private final Properties properties;

    private       int       clickCount  = 0;
    private       String    status      = "setShips";

    /**
     *
     */
    GameSummary() {
        setLayout( new CardLayout() );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Schiffe-Versenken");

        setIconImage(new ImageIcon("src\\pictures\\ship_block.png").getImage());

        //Set Properties from game:
        properties = new PropertyReader().getProperties();

        //Initialisiere Panels + Add
        player1     = new GameField( this, 1 );
        player2     = new GameField( this, 2 );
        pausePanel  = new PausePanel(this);

        add( player1 );
        add( player2 );
        add( pausePanel );

    }

    /**
     * initialise the Game.
     */
    public void init() {
        System.out.println("Game-Status: " + getStatus());
        setMinimumSize( new Dimension( Integer.parseInt(getProperty("width")), Integer.parseInt(getProperty("height")) ) );
        setVisible( true );

        player1.setVisible( true );
        player2.setVisible( false );
        pausePanel.setVisible( false );

    }

    /**
     * @param pausedPlayer
     * Pauses the Game for Playerchange.
     * Saves the id of the Player who paused the game.
     */
    public void pauseGame(int pausedPlayer) {

        this.pausedPlayer = pausedPlayer;

        player1.setVisible( false );
        player2.setVisible( false );
        pausePanel.setVisible( true );
    }


    /**
     * Resume game to next Player.
     */
    public void resumeGame() {

        if(pausedPlayer == 2) { //Player 2 paused game:
            player1.setVisible( true );
            player2.setVisible( false );
            pausePanel.setVisible( false );

        } else if (pausedPlayer == 1) { //Player 1 paused game:
            player1.setVisible( false );
            player2.setVisible( true );
            pausePanel.setVisible( false );
        }
    }


    /**
     * Starts the real Game after all own ships are set.
     */
    public void startGame() {

        //Swap Ship-arrays, so enemy-Ships can be destroyed.
        int[][] tempShips = player1.getPlayground().getaShips();

        player1.getPlayground().setaShips( player2.getPlayground().getaShips() );
        player2.getPlayground().setaShips( tempShips );

        //Aktiviere Spielbuttons
        player1.getPlayground().activateField();
        player2.getPlayground().activateField();

        setStatus( "battle" );

        System.out.println("Game-Status: " + getStatus());
    }

    //Getter and Setter:

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount( int clickCount ) {
        this.clickCount = clickCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public String getProperty( String prop) {
        return properties.getProperty( prop );
    }

    public GameField getCurrentPlayer(int id) {
        if(id==1) return player1;
        else return player2;
    }

}
