/** 
 * Determines the files to use for loading the configuration.
 * @param envVars The environment variables converted to Flyway properties.
 * @return The configuration files.
 */
private List<File> determineConfigFiles(Map<String,String> envVars){
  List<File> configFiles=new ArrayList<>();
  if (envVars.containsKey(ConfigUtils.CONFIG_FILES)) {
    for (    String file : StringUtils.tokenizeToStringArray(envVars.get(ConfigUtils.CONFIG_FILES),",")) {
      configFiles.add(toFile(file));
    }
    return configFiles;
  }
  if (System.getProperties().containsKey(ConfigUtils.CONFIG_FILES)) {
    for (    String file : StringUtils.tokenizeToStringArray(System.getProperties().getProperty(ConfigUtils.CONFIG_FILES),",")) {
      configFiles.add(toFile(file));
    }
    return configFiles;
  }
  if (getProject().getProperties().containsKey(ConfigUtils.CONFIG_FILES)) {
    for (    String file : StringUtils.tokenizeToStringArray(System.getProperties().getProperty(ConfigUtils.CONFIG_FILES),",")) {
      configFiles.add(toFile(file));
    }
    return configFiles;
  }
  if (this.configFiles != null) {
    for (    String file : this.configFiles) {
      configFiles.add(toFile(file));
    }
    return configFiles;
  }
  if (extension.configFiles != null) {
    for (    String file : extension.configFiles) {
      configFiles.add(toFile(file));
    }
    return configFiles;
  }
  return configFiles;
}
