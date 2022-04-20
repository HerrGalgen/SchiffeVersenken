package Panels;

import GameIO.PropertyReader;
import Summaries.GameMain;
import Summaries.GameSummary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

public class Settings extends JFrame {

    private final PropertyReader reader = new PropertyReader();
    private final Properties properties = reader.getProperties();

    private final JCheckBox cbrestart = new JCheckBox("Spiel beim verlassen neu starten");
    private final String[] gameSize = {"700", "800", "900", "1000"};
    private final String[] tableSize = {"10", "11", "12", "13", "14", "15", "16", "16", "17"};

    private final JComboBox<String> cbGameSizeX = new JComboBox<>(gameSize);
    private final JComboBox<String> cbTableSizeX = new JComboBox<>(tableSize);
    private final JComboBox<String> cbGameSizeY = new JComboBox<>(gameSize);
    private final JComboBox<String> cbTableSizeY = new JComboBox<>(tableSize);


    public Settings(GameSummary gameSummary) {

        setVisible(true);
        setLayout(new GridBagLayout());

        setTitle("Einstellungen - SchiffeVersenken");

        gameSummary.setSettingsOpen(true);

        cbrestart.setSelected(Boolean.parseBoolean(properties.getProperty("restartSettingsClose")));

        setupSettings();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                getNewProperties();
                reader.setProperties(properties);
                System.out.println("----[ Einstellungen geschlossen ]-----");
                gameSummary.setSettingsOpen(false);
                if (cbrestart.isSelected()) {
                    gameSummary.dispose();
                    GameMain.main(new String[2]);
                }
            }
        });

        System.out.println("----[ Einstellungen erscheinen ]-----");
    }

    private void setupSettings() {

        cbGameSizeX.setPreferredSize(new Dimension(60, 20));
        cbGameSizeY.setPreferredSize(new Dimension(60, 20));
        cbTableSizeX.setPreferredSize(new Dimension(60, 20));
        cbTableSizeY.setPreferredSize(new Dimension(60, 20));

        ((JLabel)cbGameSizeX.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)cbGameSizeY.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)cbTableSizeX.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)cbTableSizeY.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        cbGameSizeY.setSelectedItem(properties.getProperty("gridy"));
        cbTableSizeX.setSelectedItem(properties.getProperty("gridx"));
        cbGameSizeX.setSelectedItem(properties.getProperty("width"));
        cbGameSizeY.setSelectedItem(properties.getProperty("height"));

        GridBagConstraints cons = new GridBagConstraints();
        cons.gridy = 0;
        cons.gridx = 0;
        cons.insets = new Insets(5, 10, 5, 10);
        //Tabellengröße:
        add(new JLabel("Tabelle: "), cons);
        cons.gridx++;
        add(cbTableSizeY, cons);
        cons.gridx++;
        add(cbTableSizeX, cons);


        //Spielfeldgröße:
        cons.gridx = 0;
        cons.gridy++;
        add(new JLabel("Spielfeld:"), cons);
        cons.gridx++;
        add(cbGameSizeY, cons);
        cons.gridx++;
        add(cbGameSizeX, cons);

        //Restart on close:
        cons.gridy++;
        cons.gridx = 0;
        cons.gridwidth = 2;
        add(cbrestart, cons);
    }

    private void getNewProperties() {
        properties.setProperty("gridx", (String) cbTableSizeX.getSelectedItem());
        properties.setProperty("gridy", (String) cbTableSizeY.getSelectedItem());
        properties.setProperty("width", (String) cbGameSizeX.getSelectedItem());
        properties.setProperty("height", (String) cbGameSizeY.getSelectedItem());
        properties.setProperty("restartSettingsClose", String.valueOf(cbrestart.isSelected()));

    }
}
