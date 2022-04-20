package GameIO;

import Panels.Settings;
import Summaries.GameSummary;

import java.awt.event.*;

public class KeyboardListener implements KeyListener {

    GameSummary gameSummary;

    public KeyboardListener(GameSummary gameSummary) {

        this.gameSummary = gameSummary;

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:  gameSummary.getCurrentPlayer(gameSummary.getCurrentPlayerID()).getShipPlacer().toggleSwitch(); break;
            case KeyEvent.VK_ESCAPE: if (!gameSummary.isSettingsOpen()) new Settings(gameSummary); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
