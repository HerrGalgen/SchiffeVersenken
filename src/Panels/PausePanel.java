package Panels;
import Summaries.*;
import GameIO.*;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {

    /**
     * @param gameSummary Summaries.GameSummary.
     */
    public PausePanel(GameSummary gameSummary) {
        setMinimumSize( new Dimension(600,600) );
        setVisible( true );

        //Settings p1:
        JButton resume = new JButton( "Resume" );
        resume.addActionListener( new PauseListener(gameSummary) );
        resume.setActionCommand( "resume" );

        add( resume );
    }

}
