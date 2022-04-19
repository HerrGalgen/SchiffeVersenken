package GameIO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyReader {

    Properties properties = new Properties();

    /**
     * Reads the properties of the game.
     */

    private static final String PATH_IDE = "src/game.properties";
    private static final Path   PATH     = Paths.get("src/game.properties");

    public PropertyReader() {

        try {
            properties.load(new FileInputStream(PATH_IDE));
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

    public void setProperties(Properties properties) {
        this.properties = properties;
        try {
            OutputStream output = new FileOutputStream(PATH_IDE);
            this.properties.store(output, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
