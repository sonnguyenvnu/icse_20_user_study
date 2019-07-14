/** 
 * Get a list of all Configurations, excluding the template configuration; if none exist, return an empty list
 * @return List&lt;Map&lt;String, Object&gt;&gt;
 */
public static List<Map<String,Object>> getConfigurations(){
  final ConfigurationManagementGraph configManagementGraph=getConfigGraphManagementInstance();
  return configManagementGraph.getConfigurations();
}
