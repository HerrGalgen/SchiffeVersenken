package Panels;

import Summaries.GameSummary;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinPanel extends JPanel {

    JLabel lWin = new JLabel();
    JLabel lIcon = new JLabel();

    public WinPanel( GameSummary gameSummary ) {

        setMinimumSize( new Dimension(gameSummary.getWidth(),gameSummary.getHeight()) );
        setVisible( true );
        setLayout(new GridBagLayout());

        //Allgemeine Vars:
        GridBagConstraints cons = new GridBagConstraints();
        BufferedImage img;

        try {
            img = ImageIO.read(new File("src\\pictures\\win.png"));
            lIcon = new JLabel(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        lWin.setFont(new Font("Veranda", Font.BOLD, 30));


        cons.gridx = 0;
        cons.gridy = 0;
        add(lIcon, cons);

        cons.gridx = 0;
        cons.gridy++;

        add(lWin, cons);
    }

    public void setWin(int id) {
        lWin.setText("Spieler " + id + " hat gewonnen!");
    }

}
