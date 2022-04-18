package GameIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {

    Properties properties = new Properties();

    /**
     * Reads the properties of the game.
     */

    private static final String PATH_IDE = "game.properties";
    private static final Path   PATH     = Paths.get("game.properties");

    public PropertyReader() {


        try {
            properties.load(new FileInputStream( String.valueOf( PATH.toAbsolutePath() ) ));
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
