/** 
 * Determines the encoding to use for loading the configuration.
 * @param args    The command-line arguments passed in.
 * @param envVars The environment variables converted to Flyway properties.
 * @return The encoding. (default: UTF-8)
 */
private static String determineConfigurationFileEncoding(String[] args,Map<String,String> envVars){
  if (envVars.containsKey(ConfigUtils.CONFIG_FILE_ENCODING)) {
    return envVars.get(ConfigUtils.CONFIG_FILE_ENCODING);
  }
  for (  String arg : args) {
    if (isPropertyArgument(arg) && ConfigUtils.CONFIG_FILE_ENCODING.equals(getArgumentProperty(arg))) {
      return getArgumentValue(arg);
    }
  }
  return "UTF-8";
}
