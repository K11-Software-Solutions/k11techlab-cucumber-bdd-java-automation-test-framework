package org.k11techlab.bdd.webuitestframework.configManager;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

public class ConfigurationManager {
    private static final Log log = LogFactory.getLog(ConfigurationManager.class);
    private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    private final CombinedConfiguration configuration;
    private final PropertyUtil propertyUtil;

    private ConfigurationManager() {
        CombinedConfiguration config=
              ConfigurationLoader.loadConfigurationsFromFolder(
                        System.getProperty("user.dir") + File.separator +"src"+
                                File.separator +"test"+ File.separator +"resources");
        propertyUtil = new PropertyUtil(config);
        this.configuration = config;
     }

     public static ConfigurationManager getInstance() {
        return INSTANCE;
    }

    public static PropertyUtil getBundle() {
        return getInstance().propertyUtil;
    }

    public static String getString(String key, String defaultString) {
        return INSTANCE.configuration.getString(key, defaultString);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public int getInt(String key) {
        return configuration.getInt(key, 0);
    }

    // Additional getter methods for other data types
    public boolean getBoolean(String key) {
        return configuration.getBoolean(key, false);
    }

    public long getLong(String key, long defaultValue) {
        return configuration.getLong(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return configuration.getFloat(key, defaultValue);
    }

    public double getDouble(String key) {
        return configuration.getDouble(key, 0.0);
    }

    // Package-private method for testing purposes
    public static void setConfigurationForTesting(CombinedConfiguration config) {
        INSTANCE.configuration.addConfiguration(config);
    }
}
