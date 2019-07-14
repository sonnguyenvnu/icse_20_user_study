/** 
 * Filters there properties to remove the Flyway Commandline-specific ones.
 * @param config The properties to filter.
 */
private static void filterProperties(Map<String,String> config){
  config.remove(ConfigUtils.JAR_DIRS);
  config.remove(ConfigUtils.CONFIG_FILES);
  config.remove(ConfigUtils.CONFIG_FILE_ENCODING);
}
