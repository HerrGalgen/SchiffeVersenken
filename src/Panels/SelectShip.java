package Panels;

import javax.swing.*;
import java.awt.*;

/**
 * The Panel show on the Start your own Ships.
 * In the game, it shows the ships from the enemy.
 */

public class SelectShip extends JPanel {

    private JRadioButton bFive  = new JRadioButton("0x Schlachtschiff (5er)");
    private JRadioButton bFour  = new JRadioButton("0x Kreuzer (4er)");
    private JRadioButton bThree = new JRadioButton("0x Zerstörer (3er)");
    private JRadioButton bTwo   = new JRadioButton("0x U-Boote (2er)");

    private int countFive = 1;
    private int countFour = 2;
    private int countThree = 3;
    private int countTwo = 4;

    ButtonGroup buttonGroup = new ButtonGroup();

    SelectShip( int id) {
        setMinimumSize( new Dimension( 600, 50 ) );

        setBackground((id == 1) ? Color.PINK : Color.LIGHT_GRAY);
        bFive.setBackground((id == 1) ? Color.PINK : Color.LIGHT_GRAY);
        bFour.setBackground((id == 1) ? Color.PINK : Color.LIGHT_GRAY);
        bThree.setBackground((id == 1) ? Color.PINK : Color.LIGHT_GRAY);
        bTwo.setBackground((id == 1) ? Color.PINK : Color.LIGHT_GRAY);

        bTwo.setSelected(true);

        setShipCount();

        buttonGroup.add(bFive);
        buttonGroup.add(bFour);
        buttonGroup.add(bThree);
        buttonGroup.add(bTwo);

        add(bFive);
        add(bFour);
        add(bThree);
        add(bTwo);
    }

    public void setShipCount() {
        bFive.setText(countFive + bFive.getText().substring(1));
        bFour.setText(countFour + bFour.getText().substring(1));
        bThree.setText(countThree + bThree.getText().substring(1));
        bTwo.setText(countTwo + bTwo.getText().substring(1));
    }

    public int getSelectedShipSize() {
        int shipSize = 0;

        if (bFive.isSelected())
            shipSize = 5;

        if (bFour.isSelected())
            shipSize = 4;

        if (bThree.isSelected())
            shipSize = 3;

        if (bTwo.isSelected())
            shipSize = 2;

        return shipSize;
    }

    public void removeShipCount(int shipSize) {

        switch(shipSize) {
            case 5 -> countFive--;
            case 4 -> countFour--;
            case 3 -> countThree--;
            case 2 -> countTwo--;
        }

        setShipCount();
    }

    public boolean isPlaceable() {
        switch(getSelectedShipSize()) {
            case 5 -> {
                return countFive != 0;
            }
            case 4 -> {
                return countFour != 0;
            }
            case 3 -> {
                return countThree != 0;
            }
            case 2 -> {
                return countTwo != 0;
            }
        }
        return false;
    }
}
