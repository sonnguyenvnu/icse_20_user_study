/** 
 * Creates or updates a configuration for a config id.
 * @param configId config id
 * @param newConfiguration the configuration
 * @param override if true, it overrides the old config completely. means it deletes all parameters even if they arenot defined in the given configuration.
 * @return old config or null if no old config existed
 * @throws IOException if configuration can not be stored
 */
public Configuration update(String configId,Configuration newConfiguration,boolean override) throws IOException {
  org.osgi.service.cm.Configuration configuration=null;
  if (newConfiguration.containsKey(ConfigConstants.SERVICE_CONTEXT)) {
    try {
      configuration=getConfigurationWithContext(configId);
    }
 catch (    InvalidSyntaxException e) {
      logger.error("Failed to lookup config for PID '{}'",configId);
    }
    if (configuration == null) {
      configuration=configurationAdmin.createFactoryConfiguration(configId,null);
    }
  }
 else {
    configuration=configurationAdmin.getConfiguration(configId,null);
  }
  Configuration oldConfiguration=toConfiguration(configuration.getProperties());
  Dictionary<String,Object> properties=getProperties(configuration);
  Set<Entry<String,Object>> configurationParameters=newConfiguration.getProperties().entrySet();
  if (override) {
    Set<String> keySet=oldConfiguration.keySet();
    for (    String key : keySet) {
      properties.remove(key);
    }
  }
  for (  Entry<String,Object> configurationParameter : configurationParameters) {
    Object value=configurationParameter.getValue();
    if (value == null) {
      properties.remove(configurationParameter.getKey());
    }
 else     if (value instanceof String || value instanceof Integer || value instanceof Boolean || value instanceof Object[] || value instanceof Collection) {
      properties.put(configurationParameter.getKey(),value);
    }
 else {
      properties.put(configurationParameter.getKey(),value.toString());
    }
  }
  configuration.update(properties);
  return oldConfiguration;
}
