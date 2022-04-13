package GameIO;

import Panels.SelectShip;
import Summaries.GameSummary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HVListener implements ActionListener {

    SelectShip selectShip;
    GameSummary gameSummary;
    int id;

    public HVListener(SelectShip selectShip, GameSummary gameSummary, int id) {
        this.selectShip = selectShip;
        this.gameSummary = gameSummary;
        this.id = id;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("toggle")){
            selectShip.toggleSwitch();
        }
        else if(e.getActionCommand().equals("next")) {
            gameSummary.setClickCount( gameSummary.getClickCount() + 1 );
            System.out.println( "next" );
            gameSummary.pauseGame(id);
            gameSummary.getCurrentPlayer(id).removeShipSelector();

            if ( gameSummary.getClickCount() == 2 )
                gameSummary.startGame();
        }

    }
}
