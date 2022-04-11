package GameIO;

import Panels.SelectShip;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HVListener implements ActionListener {

    SelectShip selectShip;

    public HVListener(SelectShip selectShip) {
        this.selectShip = selectShip;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("toggle")){
            selectShip.switchToggleText();
            selectShip.setHorizontal(!selectShip.isHorizontal());
        }

    }
}
