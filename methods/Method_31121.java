/** 
 * Initializes the config with the default configuration for the command-line tool.
 * @param config The config object to initialize.
 */
private static void initializeDefaults(Map<String,String> config){
  config.put(ConfigUtils.LOCATIONS,"filesystem:" + new File(getInstallationDir(),"sql").getAbsolutePath());
  config.put(ConfigUtils.JAR_DIRS,new File(getInstallationDir(),"jars").getAbsolutePath());
}
