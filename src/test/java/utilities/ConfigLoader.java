package utilities;

import constants.Environment;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;

    public ConfigLoader() {
        String environment = System.getProperty("env", String.valueOf(Environment.DEV));
        switch (Environment.valueOf(environment.toUpperCase())) {
            case DEV:
                properties = PropertyUtils.propertyLoader("src/test/resources/devconfig.properties");
                break;
            case PROD:
                properties = PropertyUtils.propertyLoader("src/test/resources/prodconfig.properties");
                break;
            default:
                throw new RuntimeException("Invalid Environment: " + environment);
        }
    }

    public String getProperty(String property) {
        String prop = properties.getProperty(property);
        if (prop != null)
            return prop;
        else
            throw new RuntimeException("Property " + property + " is not specified in the config file");
    }

}
