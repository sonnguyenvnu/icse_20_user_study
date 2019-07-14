/** 
 * Determines the encoding to use for loading the configuration files.
 * @param envVars The environment variables converted to Flyway properties.
 * @return The encoding. (default: UTF-8)
 */
private String determineConfigurationFileEncoding(Map<String,String> envVars){
  if (envVars.containsKey(ConfigUtils.CONFIG_FILE_ENCODING)) {
    return envVars.get(ConfigUtils.CONFIG_FILE_ENCODING);
  }
  if (System.getProperties().containsKey(ConfigUtils.CONFIG_FILE_ENCODING)) {
    return System.getProperties().getProperty(ConfigUtils.CONFIG_FILE_ENCODING);
  }
  if (configFileEncoding != null) {
    return configFileEncoding;
  }
  return "UTF-8";
}
