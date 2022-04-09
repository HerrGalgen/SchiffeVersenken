package Listeners;

import Summaries.GameSummary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseListener implements ActionListener {

    GameSummary gameSummary;

    public PauseListener(GameSummary gameSummary) {
        this.gameSummary = gameSummary;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals( "resume" ))
            gameSummary.resumeGame();
    }
}
