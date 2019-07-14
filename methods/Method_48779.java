/** 
 * Update template configuration by updating supplied existing properties and adding new ones to the {@link Configuration}; your updated Configuration may not contain the property "graph.graphname"; NOTE: Any graph using a configuration that was created using the template configuration must-- 1) be closed and reopened on every JanusGraph Node 2) have its corresponding Configuration removed and 3) recreate the graph-- before the update is guaranteed to take effect.
 */
public void updateTemplateConfiguration(final Configuration config){
  Preconditions.checkArgument(!config.containsKey(PROPERTY_GRAPH_NAME),String.format("Your updated template configuration may not contain the property \"%s\".",PROPERTY_GRAPH_NAME));
  log.warn("Any graph configuration created using the template configuration are only guaranteed to have their configuration updated " + "according to this new template configuration when the graph in question has been closed on every Janus Graph Node, its " + "corresponding Configuration has been removed, and the graph has been recreated.");
  updateVertexWithProperties(PROPERTY_TEMPLATE,true,ConfigurationConverter.getMap(config));
}
