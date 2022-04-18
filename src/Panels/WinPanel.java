package Panels;

import javax.swing.*;

public class WinPanel extends JPanel {

    JLabel lWin = new JLabel();

    public WinPanel() {
        add(lWin);
    }

    public void setWin(int id) {
        lWin.setText("Spieler " + id + " hat gewonnen!");
    }

}
