package Panels;

import GameIO.PropertyReader;
import Summaries.GameSummary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Properties;

public class Settings extends JFrame {

    GameSummary gameSummary;
    PropertyReader reader = new PropertyReader();
    Properties properties = reader.getProperties();

    String[] names = new String[properties.size()];
    String[] values = new String[properties.size()];

    JLabel[] labelNames = new JLabel[properties.size()];
    JTextField[] fieldValues = new JTextField[properties.size()];

    public Settings(GameSummary gameSummary) {

        this.gameSummary = gameSummary;
        setVisible(true);
        setMinimumSize(new Dimension(gameSummary.getWidth(), gameSummary.getHeight()));
        setLayout(new GridBagLayout());
        setTitle("Einstellungen - SchiffeVersenken");

        gameSummary.setSettingsOpen(true);

        GridBagConstraints cons = new GridBagConstraints();

        int i = 0;

        for(Map.Entry<Object, Object> entry : properties.entrySet()) {
            names[i] = (String) entry.getKey();
            values[i] = (String) entry.getValue();
            i++;
        }

        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5,10,5,10);

        for(int index = 0; index < properties.size(); index++) {
            //add labels for Settings:
            cons.gridx = 0;
            labelNames[index] = new JLabel(names[index]);
            labelNames[index].setFont(new Font("Dialog", Font.PLAIN, 20));
            labelNames[index].setAlignmentX(JLabel.LEFT_ALIGNMENT);
            add(labelNames[index], cons);

            cons.gridx++;

            //Add Text-Boxes for Settings:
            fieldValues[index] = new JTextField(values[index]);
            fieldValues[index].setFont(new Font("Dialog", Font.PLAIN, 20));
            fieldValues[index].setAlignmentX(JLabel.LEFT_ALIGNMENT);
            fieldValues[index].setPreferredSize(new Dimension(80,30));
            add(fieldValues[index], cons);
            cons.gridy++;
        }

        cons.gridx++;
        cons.gridy = 0;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                getNewProperties();
                reader.setProperties(properties);
                System.out.println("----[ Einstellungen geschlossen ]-----");
                gameSummary.setSettingsOpen(false);
            }
        });

        System.out.println("----[ Einstellungen erscheinen ]-----");
    }

    private void getNewProperties() {
        int i = 0;
        for(JTextField field : fieldValues) {
            properties.setProperty(names[i], field.getText());
            i++;
        }
    }


}
