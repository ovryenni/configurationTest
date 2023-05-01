package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class ConfigurationReader {

    // Reload every 10 minutes = 600 seconds = 600K ms
    private static final long reloadRefreshRateInMillis = 600000L;

    private static final CompositeConfiguration configurationTree = new CompositeConfiguration();

    private static final Map<String, PropertiesConfiguration> fileLoadMap =
            new HashMap<String, PropertiesConfiguration>();

    /**
     * Method to load a configuration file
     * This method can throw
     * @param filePath
     * @throws ConfigurationException
     */
    public static synchronized void loadConfigFile(String filePath)
            throws ConfigurationException
    {
        if (fileLoadMap.containsKey(filePath)) {
            return;
        } else {

            FileChangedReloadingStrategy reloadingStrategy = new FileChangedReloadingStrategy();
            reloadingStrategy.setRefreshDelay(reloadRefreshRateInMillis);

            PropertiesConfiguration propConfig = new PropertiesConfiguration(filePath);
            propConfig.setReloadingStrategy(reloadingStrategy);
            propConfig.clear();
            propConfig.load();

            configurationTree.addConfiguration(propConfig);
            fileLoadMap.put(filePath, propConfig);
        }
    }

    public static synchronized void setConfigFileReloadRate(String filePath, long refreshInMillis)
    {
        if (fileLoadMap.containsKey(filePath)) {
            PropertiesConfiguration propConfig = fileLoadMap.get(filePath);
            FileChangedReloadingStrategy reloadStrategy =
                    (FileChangedReloadingStrategy) propConfig.getReloadingStrategy();
            reloadStrategy.setRefreshDelay(refreshInMillis);
        }
    }

    public static int getInt(String configString, int defaultValue)
    {
        return configurationTree.getInt(configString, defaultValue);
    }

    public static String getString(String configString, String defaultValue)
    {
        return configurationTree.getString(configString, defaultValue);
    }

    public static String getString(String configString)
    {
        return configurationTree.getString(configString);
    }

    public static List<Object> getList(String configString)
    {
        return configurationTree.getList(configString);
    }

    public static String[] getStringArray(String configString)
    {
        return configurationTree.getStringArray(configString);
    }

    public static double getDouble(String configString, double defaultValue)
    {
        return configurationTree.getDouble(configString, defaultValue);
    }
}