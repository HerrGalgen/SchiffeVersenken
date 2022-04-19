package GameIO;

import Summaries.GameSummary;

import java.awt.event.*;

public record KeyboardListener(GameSummary gameSummary) implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_R -> gameSummary.getCurrentPlayer(gameSummary.getCurrentPlayerID()).getShipPlacer().toggleSwitch();
            case KeyEvent.VK_ESCAPE -> System.out.println("ESC");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}
