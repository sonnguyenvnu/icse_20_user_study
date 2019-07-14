/** 
 * Determines the files to use for loading the configuration.
 * @param args    The command-line arguments passed in.
 * @param envVars The environment variables converted to Flyway properties.
 * @return The configuration files.
 */
private static List<File> determineConfigFilesFromArgs(String[] args,Map<String,String> envVars){
  List<File> configFiles=new ArrayList<>();
  if (envVars.containsKey(ConfigUtils.CONFIG_FILES)) {
    for (    String file : StringUtils.tokenizeToStringArray(envVars.get(ConfigUtils.CONFIG_FILES),",")) {
      configFiles.add(new File(file));
    }
    return configFiles;
  }
  for (  String arg : args) {
    String argValue=getArgumentValue(arg);
    if (isPropertyArgument(arg) && ConfigUtils.CONFIG_FILES.equals(getArgumentProperty(arg))) {
      for (      String file : StringUtils.tokenizeToStringArray(argValue,",")) {
        configFiles.add(new File(file));
      }
    }
  }
  return configFiles;
}
