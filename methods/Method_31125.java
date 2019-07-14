/** 
 * Loads the configuration from the various possible locations.
 * @param config  The properties object to load to configuration into.
 * @param args    The command-line arguments passed in.
 * @param envVars The environment variables, converted into properties.
 */
static void loadConfigurationFromConfigFiles(Map<String,String> config,String[] args,Map<String,String> envVars){
  String encoding=determineConfigurationFileEncoding(args,envVars);
  config.putAll(ConfigUtils.loadConfigurationFile(new File(getInstallationDir() + "/conf/" + ConfigUtils.CONFIG_FILE_NAME),encoding,false));
  config.putAll(ConfigUtils.loadConfigurationFile(new File(System.getProperty("user.home") + "/" + ConfigUtils.CONFIG_FILE_NAME),encoding,false));
  config.putAll(ConfigUtils.loadConfigurationFile(new File(ConfigUtils.CONFIG_FILE_NAME),encoding,false));
  for (  File configFile : determineConfigFilesFromArgs(args,envVars)) {
    config.putAll(ConfigUtils.loadConfigurationFile(configFile,encoding,true));
  }
}
