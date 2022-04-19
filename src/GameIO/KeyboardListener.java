package GameIO;

import Summaries.GameSummary;
import Summaries.PlayerSummary;

import java.awt.event.*;

public class KeyboardListener implements KeyListener {

    private final GameSummary gameSummary;
    private PlayerSummary player;

    public KeyboardListener(GameSummary gameSummary) {
        this.gameSummary = gameSummary;
    }

    @Override
    public void keyTyped( KeyEvent e ) {


    }

    @Override
    public void keyPressed( KeyEvent e ) {

        switch(e.getKeyCode()) {
            case KeyEvent.VK_R -> {
                player = gameSummary.getCurrentPlayer(gameSummary.getCurrentPlayerID());
                player.getShipPlacer().toggleSwitch();
            }
            case KeyEvent.VK_ESCAPE -> System.out.println("ESC");
        }
    }

    @Override
    public void keyReleased( KeyEvent e ) {


    }
}
