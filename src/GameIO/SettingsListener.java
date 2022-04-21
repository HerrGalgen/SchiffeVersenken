package GameIO;

import Panels.Settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsListener implements ActionListener {

    private Settings settings;

    public SettingsListener(Settings settings) {
        this.settings = settings;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "col1":
                settings.changeColor(1);
                break;
            case "col2":
                settings.changeColor(2);
                break;
        }

    }
}
