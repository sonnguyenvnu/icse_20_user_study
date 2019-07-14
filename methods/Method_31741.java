/** 
 * Determines the files to use for loading the configuration.
 * @param workDir The working directory to use.
 * @param envVars The environment variables converted to Flyway properties.
 * @return The configuration files.
 */
private List<File> determineConfigFiles(File workDir,Map<String,String> envVars){
  List<File> configFiles=new ArrayList<>();
  if (envVars.containsKey(ConfigUtils.CONFIG_FILES)) {
    for (    String file : StringUtils.tokenizeToStringArray(envVars.get(ConfigUtils.CONFIG_FILES),",")) {
      configFiles.add(toFile(workDir,file));
    }
    return configFiles;
  }
  if (System.getProperties().containsKey(ConfigUtils.CONFIG_FILES)) {
    for (    String file : StringUtils.tokenizeToStringArray(System.getProperties().getProperty(ConfigUtils.CONFIG_FILES),",")) {
      configFiles.add(toFile(workDir,file));
    }
    return configFiles;
  }
  if (mavenProject.getProperties().containsKey(ConfigUtils.CONFIG_FILES)) {
    for (    String file : StringUtils.tokenizeToStringArray(mavenProject.getProperties().getProperty(ConfigUtils.CONFIG_FILES),",")) {
      configFiles.add(toFile(workDir,file));
    }
  }
 else   if (this.configFiles != null) {
    configFiles.addAll(Arrays.asList(this.configFiles));
  }
  return configFiles;
}
