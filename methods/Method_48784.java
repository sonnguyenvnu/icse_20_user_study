/** 
 * Get template configuration if exists, else return null.
 * @return Map&lt;String, Object&gt;
 */
public Map<String,Object> getTemplateConfiguration(){
  final List<Map<Object,Object>> templateConfigurations=graph.traversal().V().has(PROPERTY_TEMPLATE,true).valueMap().toList();
  if (templateConfigurations.size() == 0)   return null;
  if (templateConfigurations.size() > 1) {
    log.warn("Your configuration management graph is an a bad state. Please " + "ensure you have just one template configuration. The behavior " + "of the class' APIs are henceforth unpredictable until this is fixed.");
  }
  templateConfigurations.get(0).remove(PROPERTY_TEMPLATE);
  return deserializeVertexProperties(templateConfigurations.get(0));
}
