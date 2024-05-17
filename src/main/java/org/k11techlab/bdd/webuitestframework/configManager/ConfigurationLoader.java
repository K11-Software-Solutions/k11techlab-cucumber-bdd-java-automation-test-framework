package org.k11techlab.bdd.webuitestframework.configManager;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.FilenameFilter;

public class ConfigurationLoader {

    public static CombinedConfiguration loadConfigurationsFromFolder(String folderPath) {
        File dir = new File(folderPath);
        FilenameFilter filter = (dir1, name) -> name.endsWith(".xml") || name.endsWith(".properties");

        File[] files = dir.listFiles(filter);
        if (files == null) {
            System.out.println("No configuration files found in the specified folder.");
            return null;
        }

        CombinedConfiguration combinedConfig = new CombinedConfiguration();
        Parameters params = new Parameters();

        for (File file : files) {
            try {
                FileBasedConfigurationBuilder<? extends Configuration> builder;
                if (file.getName().endsWith(".xml")) {
                    builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class)
                                .configure(params.xml().setFile(file));
                } else { // .properties
                    builder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                                .configure(params.properties().setFile(file));
                }

                Configuration config = builder.getConfiguration();
                combinedConfig.addConfiguration(config);
            } catch (ConfigurationException e) {
                System.err.println("Error loading configuration from file: " + file.getName());
                e.printStackTrace();
            }
        }

        return combinedConfig;
    }
}
