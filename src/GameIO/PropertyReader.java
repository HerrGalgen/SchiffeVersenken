package GameIO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {

    private Properties properties = new Properties();

    /**
     * Reads the properties of the game.
     */

    private static final String PATH = "src/game.properties";

    public PropertyReader() {

            try {
                properties.load(new FileInputStream(PATH));
            } catch (Exception e) {
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

    public void setProperties(Properties properties) {
        this.properties = properties;
        try {
            System.out.println("src" + PATH);
            OutputStream out = new FileOutputStream("src/game.properties");
            this.properties.store(out, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
