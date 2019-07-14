/** 
 * Get Configuration according to supplied graphName mapped to a specific {@link Graph}; if does not exist, return null.
 * @return Map&lt;String, Object&gt;
 */
public static Map<String,Object> getConfiguration(final String configName){
  final ConfigurationManagementGraph configManagementGraph=getConfigGraphManagementInstance();
  return configManagementGraph.getConfiguration(configName);
}
