/** 
 * Filters there properties to remove the Flyway Maven Plugin-specific ones to avoid warnings.
 * @param conf The properties to filter.
 */
private static void removeMavenPluginSpecificPropertiesToAvoidWarnings(Map<String,String> conf){
  conf.remove(ConfigUtils.CONFIG_FILES);
  conf.remove(ConfigUtils.CONFIG_FILE_ENCODING);
  conf.remove(CONFIG_CURRENT);
  conf.remove(CONFIG_SKIP);
  conf.remove(CONFIG_VERSION);
  conf.remove(CONFIG_SERVER_ID);
  conf.remove(CONFIG_WORKING_DIRECTORY);
}
