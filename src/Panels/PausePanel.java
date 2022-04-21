package Panels;
import Summaries.*;
import GameIO.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PausePanel extends JPanel {

    /**
     * @param gameSummary Summaries.GameSummary.
     */
    public PausePanel(GameSummary gameSummary) {
        System.out.println("---[ PausePanel erstellt ]---");

        setMinimumSize( new Dimension(gameSummary.getWidth(),gameSummary.getHeight()) );
        setVisible( true );
        setLayout(new GridBagLayout());

        //Allgemeine Vars:
        GridBagConstraints cons = new GridBagConstraints();
        BufferedImage img;
        JLabel lBoat = new JLabel();


        try {
             img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/win.png")));
             lBoat = new JLabel(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Panel-Content:
        JButton resume = new JButton( "Resume" );
        JLabel lTitle = new JLabel(gameSummary.getTitle());


        lTitle.setFont(new Font("Veranda", Font.BOLD, 30));

        resume.addActionListener( new PauseListener(gameSummary) );
        resume.setActionCommand( "resume" );

        cons.gridx = 0;
        cons.gridy = 0;
        add(lBoat, cons);

        cons.gridx = 0;
        cons.gridy++;

        add(lTitle, cons);

        cons.gridx = 0;
        cons.gridy++;

        add( resume , cons);

    }

}
