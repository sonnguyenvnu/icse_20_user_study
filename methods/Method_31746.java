/** 
 * Retrieve the properties from the config files (if specified).
 * @param envVars The environment variables converted to Flyway properties.
 * @return The properties.
 */
private Map<String,String> loadConfigurationFromDefaultConfigFiles(Map<String,String> envVars){
  String encoding=determineConfigurationFileEncoding(envVars);
  File configFile=new File(System.getProperty("user.home") + "/" + ConfigUtils.CONFIG_FILE_NAME);
  return new HashMap<>(ConfigUtils.loadConfigurationFile(configFile,encoding,false));
}
