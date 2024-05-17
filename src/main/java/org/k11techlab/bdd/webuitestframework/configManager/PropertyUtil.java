package org.k11techlab.bdd.webuitestframework.configManager;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.Properties;

public class PropertyUtil  {
    private static final Log logger = LogFactory.getLog(PropertyUtil.class);
    private static CombinedConfiguration configuration;

    public PropertyUtil() {
        this.configuration = new CombinedConfiguration();
        loadSystemProperties();
    }
    public PropertyUtil(CombinedConfiguration combinedConfig) {
        this.configuration = combinedConfig;
        loadSystemProperties();
    }

    private void loadSystemProperties() {
        Properties sysProps = System.getProperties();
        PropertiesConfiguration sysConfig = new PropertiesConfiguration();
        sysProps.forEach((key, value) -> {
            if (key.toString().matches("^(?!sun\\.|java\\.).*")) {
                sysConfig.addProperty(key.toString(), value);
            }
        });
        configuration.addConfiguration(sysConfig);
    }


    public PropertyUtil(String... file) {
        this();
        load(file);
    }


    public PropertyUtil(File... file) {
        this();
        load(file);
    }

    public boolean load(String... files) {
        boolean result = true;
        Configurations configs = new Configurations(); // Assuming Configurations is used for loading

        for (String filePath : files) {
            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    logger.error("File does not exist: " + filePath);
                    result = false;
                    continue;
                }

                // Assuming usage of PropertiesConfiguration or similar
                Configuration config = configs.properties(file);
                // Assuming there's a way to combine this newly loaded configuration
                this.configuration.addConfiguration(config);

            } catch (ConfigurationException ce) {
                logger.error("Configuration error loading file: " + filePath, ce);
                result = false;
            } catch (Exception e) {
                logger.error("Unexpected error loading file: " + filePath, e);
                result = false;
            }
        }
        return result;
    }



    public void loadConfiguration(String configFilePath) {
        Configurations configs = new Configurations();
        try {
            if (configFilePath.endsWith(".xml")) {
                configuration.addConfiguration(configs.xml(new File(configFilePath)));
            } else {
                configuration.addConfiguration(configs.properties(new File(configFilePath)));
            }
        } catch (ConfigurationException e) {
            logger.error("Failed to load configuration file: " + configFilePath, e);
        }
    }

    public boolean load(File... files) {
        boolean result = true;
        for (File file : files) {
            try {
                if (file.getName().endsWith("xml") || file.getName().contains(".xml.")) {
                    configuration.addConfiguration(new Configurations().xml(file));
                } else {
                    configuration.addConfiguration(new Configurations().properties(file));
                }
            } catch (ConfigurationException e) {
                logger.error("Failed to load file: " + file.getAbsolutePath(), e);
                result = false;
            }
        }
        return result;
    }

    public static String getString(String key, String defaultString) {
        return ConfigurationManager.getInstance().getString(key, defaultString);

    }

    public static int getInt(String key, int defaultIntValue) {
        return configuration.getInt(key, defaultIntValue);
    }

    public static boolean getBoolean(String key) {
        return configuration.getBoolean(key, false);
    }

    public static double getDouble(String key) {
        return configuration.getDouble(key, 0.0);
    }

     /**
     * @param sPropertyName
     * @return property-key value if key presents or key otherwise.
     */
    public String getPropertyValue(String sPropertyName) {
        return getString(sPropertyName, sPropertyName);
    }

    /**
     * @param sPropertyName
     * @return property-key value if key presents or null otherwise
     */
    public String getPropertyValueOrNull(String sPropertyName) {
        return getString(sPropertyName, "");
    }



    // Method to add or update a property in the configuration
    public void addProperty(String key, Object value) {
        configuration.addProperty(key, value);
        logger.info("Property added/updated: " + key + " = " + value);
    }

    // Method to update an existing property or add it if it doesn't exist
    public void editProperty(String key, Object value) {
        configuration.setProperty(key, value);
        logger.info("Property edited/updated: " + key + " = " + value);
    }

    // Method to remove a property from the configuration
    public void clearProperty(String key) {
        configuration.clearProperty(key);
        logger.info("Property cleared: " + key);
    }


}

