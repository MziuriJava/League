package ge.mziuri.league.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Messages {

    private static Properties properties;

    public static String getMessage(String key) {
        if (properties == null) {
            try {
                File file = new File("Messages_EN.properties");
                FileInputStream fileInput = new FileInputStream(file);
                properties = new Properties();
                properties.load(fileInput);
                fileInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }
}
