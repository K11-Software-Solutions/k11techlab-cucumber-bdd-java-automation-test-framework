package org.k11techlab.bdd.coreframework.configurationmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    static Properties prop;
    FileInputStream input = null;

    public LoadProperties() {
        try {
            File source = new File("resources/test-config.properties");

            FileInputStream input = new FileInputStream(source);

            prop = new Properties();

            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }




    public static String getProperty(String key)
    {
        return prop.getProperty(key);
    }
}


