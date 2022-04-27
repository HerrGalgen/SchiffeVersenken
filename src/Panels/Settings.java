package Panels;

import GameIO.PropertyReader;
import GameIO.SettingsListener;
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

    private final SettingsListener listener = new SettingsListener(this);

    private final JCheckBox cbrestart = new JCheckBox("Spiel beim verlassen neu starten");
    private final String[] gameSize = {"700", "800", "900", "1000"};
    private final String[] tableSize = {"10", "11", "12", "13", "14", "15", "16", "16", "17"};

    private final JComboBox<String> cbGameSizeX = new JComboBox<>(gameSize);
    private final JComboBox<String> cbGameSizeY = new JComboBox<>(gameSize);
    private final JComboBox<String> cbTableSize = new JComboBox<>(tableSize);

    private final JButton bColorP1 = new JButton("Spielerfarbe 1");
    private final JButton bColorP2 = new JButton("Spielerfarbe 2");

    private Color colorP1 = null;
    private Color colorP2 = null;

    private final GameSummary gameSummary;


    public Settings(GameSummary gameSummary) {

        this.gameSummary = gameSummary;

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

        pack();

        System.out.println("----[ Einstellungen erscheinen ]-----");
    }

    private void setupSettings() {

        cbGameSizeX.setPreferredSize(new Dimension(60, 20));
        cbGameSizeY.setPreferredSize(new Dimension(60, 20));
        cbTableSize.setPreferredSize(new Dimension(60, 20));

        ((JLabel)cbGameSizeX.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)cbGameSizeY.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel)cbTableSize.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        cbTableSize.setSelectedItem(properties.getProperty("grid"));
        cbGameSizeX.setSelectedItem(properties.getProperty("width"));
        cbGameSizeY.setSelectedItem(properties.getProperty("height"));


        colorP1 = Color.decode(gameSummary.getProperty("p1Color"));
        colorP2 = Color.decode(gameSummary.getProperty("p2Color"));

        bColorP1.addActionListener(listener);
        bColorP1.setActionCommand("col1");
        bColorP1.setBackground(colorP1);

        bColorP2.addActionListener(listener);
        bColorP2.setActionCommand("col2");
        bColorP2.setBackground(colorP2);

        //Add all Things:

        GridBagConstraints cons = new GridBagConstraints();
        cons.gridy = 0;
        cons.gridx = 0;
        cons.insets = new Insets(5, 10, 5, 10);
        //Tabellengröße:
        add(new JLabel("Tabelle: "), cons);
        cons.gridx++;
        add(cbTableSize, cons);

        //Spielfeldgröße:
        cons.gridx = 0;
        cons.gridy++;
        add(new JLabel("Spielfeld:"), cons);
        cons.gridx++;
        add(cbGameSizeY, cons);
        cons.gridx++;
        add(cbGameSizeX, cons);

        //Colorpicker:
        cons.gridx = 0;
        cons.gridy++;
        add(bColorP1, cons);
        cons.gridx++;
        add(bColorP2, cons);

        //Restart on close:
        cons.gridy++;
        cons.gridx = 0;
        cons.gridwidth = 2;
        add(cbrestart, cons);
    }

    public void changeColor(int playerID) {

        if(playerID == 1) {
            colorP1 = JColorChooser.showDialog(null, "Spielerfarbe - 1", Color.decode(gameSummary.getProperty("p1Color")));
            if(colorP1 == null)
                colorP1 = Color.decode(gameSummary.getProperty("p1Color"));
            bColorP1.setBackground(colorP1);
        } else {
            colorP2 = JColorChooser.showDialog(null, "Spielerfarbe - 2", Color.decode(gameSummary.getProperty("p2Color")));
            if(colorP2 == null)
                colorP2 = Color.decode(gameSummary.getProperty("p2Color"));
            bColorP2.setBackground(colorP2);
        }

    }

    private void getNewProperties() {
        properties.setProperty("grid", (String) cbTableSize.getSelectedItem());
        properties.setProperty("width", (String) cbGameSizeX.getSelectedItem());
        properties.setProperty("height", (String) cbGameSizeY.getSelectedItem());
        properties.setProperty("restartSettingsClose", String.valueOf(cbrestart.isSelected()));
        properties.setProperty("p1Color", String.format("0x%02x%02x%02x", colorP1.getRed(), colorP1.getGreen(), colorP1.getBlue()));
        properties.setProperty("p2Color", String.format("0x%02x%02x%02x", colorP2.getRed(), colorP2.getGreen(), colorP2.getBlue()));

    }
}
