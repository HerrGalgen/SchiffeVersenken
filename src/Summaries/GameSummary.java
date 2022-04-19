package Summaries;

import GameIO.KeyboardListener;
import GameIO.PropertyReader;
import Panels.PausePanel;
import Panels.WinPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * The whole Summaries.GameSummary. It creates the two Players and switches between them.
 */
public class GameSummary extends JFrame {

    private static final Color          COLOR_P1     = new Color( 0x5EFF91 );
    private static final Color          COLOR_P2     = new Color( 0x5EB1FF );
    private int                         pausedPlayer = 0;
    private              Properties     properties;

    private              PlayerSummary  player1;
    private              PlayerSummary  player2;
    private              PausePanel     pausePanel;
    private              WinPanel       winPanel;
    private int                         clickCount   = 0;
    private              String         status       = "setShips";
    private static       boolean        settingsOpen = false;

    /**
     *
     */
    GameSummary() {
        setLayout( new CardLayout() );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Schiffe-Versenken" );

        addKeyListener( new KeyboardListener(this) );
        setFocusable( true );


        setIconImage( new ImageIcon( "src\\pictures\\ship_block.png" ).getImage() );

        //Set Properties from game:
        properties = new PropertyReader().getProperties();

        //Initialisiere Panels + Add
        player1 = new PlayerSummary( this, 1 );
        player2 = new PlayerSummary( this, 2 );
        pausePanel = new PausePanel( this );
        winPanel = new WinPanel(this);
    }

    /**
     * initialise the Game.
     */
    public void init() {
        status = "setShips";
        System.out.println("-----[ Game-Status: " + getStatus() + " ]-----");
        setMinimumSize( new Dimension( Integer.parseInt(getProperty("width")), Integer.parseInt(getProperty("height")) ) );
        setVisible( true );

        add( player1 );
        add( player2 );
        add( pausePanel );
        add( winPanel );

        player1.setVisible( true );
        player2.setVisible( false );
        pausePanel.setVisible( false );
        winPanel.setVisible( false );

        pack();

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

    public int getCurrentPlayerID() {
        if(player1.isVisible())
            return 1;
        if(player2.isVisible())
            return 2;
        return 0;
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

        //Swap Ship-hashmaps:
        HashMap<Integer, Integer> tempIDSize = player1.getPlayground().getShipIDSize();

        player1.getPlayground().setShipIDSize( player2.getPlayground().getShipIDSize() );
        player2.getPlayground().setShipIDSize( tempIDSize );

        //Aktiviere Spielbuttons
        player1.getPlayground().activateField();
        player2.getPlayground().activateField();

        player1.getShipShower().setShipsLeft( player2.getShipPlacer().getPlacedShips() );
        player2.getShipShower().setShipsLeft( player1.getShipPlacer().getPlacedShips() );

        setStatus( "battle" );

        System.out.println( "-----[ Game-Status: " + getStatus() + " ]-----");
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

    public static Color getColorP1() {
        return COLOR_P1;
    }

    public static Color getColorP2() {
        return COLOR_P2;
    }

    public String getProperty( String prop ) {
        return properties.getProperty( prop );
    }

    public PlayerSummary getCurrentPlayer( int id ) {
        if ( id == 1 ) return player1;
        else return player2;
    }

    public void setWin(int id) {
        winPanel.setWin( id );

        player1.setVisible( false );
        player2.setVisible( false );
        pausePanel.setVisible( false );
        winPanel.setVisible( true );
    }

    public void resetGame() {
        System.out.println("-----[ Spiel wird resettet ]-----");

        clickCount = 0;
        pausedPlayer = 0;

        remove( player1 );
        remove( player2 );
        remove( pausePanel );
        remove( winPanel );

        //Set Properties from game:
        properties = new PropertyReader().getProperties();

        //Initialisiere Panels + Add
        player1 = new PlayerSummary( this, 1 );
        player2 = new PlayerSummary( this, 2 );
        pausePanel = new PausePanel( this );
        winPanel = new WinPanel(this);

        init();

    }

    public boolean isSettingsOpen() {
        return settingsOpen;
    }

    public void setSettingsOpen(boolean settingsOpen) {
        GameSummary.settingsOpen = settingsOpen;
    }
}
