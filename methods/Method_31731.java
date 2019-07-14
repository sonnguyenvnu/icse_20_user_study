/** 
 * Filters there properties to remove the Flyway Gradle Plugin-specific ones to avoid warnings.
 * @param conf The properties to filter.
 */
private static void removeGradlePluginSpecificPropertiesToAvoidWarnings(Map<String,String> conf){
  conf.remove(ConfigUtils.CONFIG_FILES);
  conf.remove(ConfigUtils.CONFIG_FILE_ENCODING);
  conf.remove(ConfigUtils.CONFIGURATIONS);
  conf.remove("flyway.version");
}
