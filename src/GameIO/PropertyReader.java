package GameIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    Properties properties = new Properties();

    /**
     * Reads the properties of the game.
     */

    public PropertyReader() {
        try {
            properties.load(new FileInputStream("game.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.list( System.out );
    }

    /**
     *
     * @return current Properties
     */
    public Properties getProperties() {
        return properties;
    }
}
