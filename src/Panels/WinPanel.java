package Panels;

import GameIO.WinPanListener;
import Summaries.GameSummary;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class WinPanel extends JPanel {

    private final JLabel lWin = new JLabel();

    public WinPanel( GameSummary gameSummary ) {

        setMinimumSize( new Dimension(gameSummary.getWidth(),gameSummary.getHeight()) );
        setLayout(new GridBagLayout());

        //Allgemeine Vars:
        GridBagConstraints cons = new GridBagConstraints();
        BufferedImage img;

        JLabel lIcon = new JLabel();
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/pictures/win.png")));
            lIcon = new JLabel(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        lWin.setFont(new Font("Veranda", Font.BOLD, 30));
        JButton bReset = new JButton("Reset Game");
        bReset.setActionCommand( "reset" );
        bReset.addActionListener( new WinPanListener(gameSummary) );

        cons.gridx = 0;
        cons.gridy = 0;
        add(lIcon, cons);

        cons.gridy++;

        add(lWin, cons);

        cons.gridy++;

        add(bReset, cons);
    }

    public void setWin(int id) {
        lWin.setText("Spieler " + id + " hat gewonnen!");
    }

}
