/** 
 * Retrieve the properties from the config files (if specified).
 * @param envVars The environment variables converted to Flyway properties.
 * @return The properties.
 */
private Map<String,String> loadConfigurationFromConfigFiles(Map<String,String> envVars){
  String encoding=determineConfigurationFileEncoding(envVars);
  Map<String,String> conf=new HashMap<>();
  for (  File configFile : determineConfigFiles(envVars)) {
    conf.putAll(ConfigUtils.loadConfigurationFile(configFile,encoding,true));
  }
  return conf;
}
