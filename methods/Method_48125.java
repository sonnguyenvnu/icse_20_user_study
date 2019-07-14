/** 
 * Get template configuration if exists, else return null.
 * @return Map&lt;String, Object&gt;
 */
public static Map<String,Object> getTemplateConfiguration(){
  final ConfigurationManagementGraph configManagementGraph=getConfigGraphManagementInstance();
  return configManagementGraph.getTemplateConfiguration();
}
