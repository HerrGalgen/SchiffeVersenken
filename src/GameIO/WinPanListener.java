package GameIO;

import Summaries.GameSummary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinPanListener implements ActionListener {

    GameSummary gameSummary;

    public WinPanListener( GameSummary gameSummary ) {
        this.gameSummary = gameSummary;
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        if(e.getActionCommand().equals( "reset" ))
            gameSummary.resetGame();
    }
}
