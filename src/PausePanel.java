import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {

    private JButton     resume        = new JButton( "Resume" );

    PausePanel(GameSummary gameSummary) {
        setMinimumSize( new Dimension(600,600) );
        setVisible( true );

        //Settings p1:
        resume.addActionListener( new Listener(gameSummary) );
        resume.setActionCommand( "resume" );

        add(resume);
    }

}
