LinkedList<PluginClassFileTransformer> reduce(final ClassLoader classLoader,List<PluginClassFileTransformer> pluginCalls,String className){
  LinkedList<PluginClassFileTransformer> reduced=new LinkedList<>();
  Map<String,PluginClassFileTransformer> fallbackMap=new HashMap<>();
  for (  PluginClassFileTransformer pcft : pluginCalls) {
    try {
      String pluginGroup=pcft.getPluginGroup();
      if (pcft.versionMatches(classLoader)) {
        if (pluginGroup != null) {
          fallbackMap.put(pluginGroup,null);
        }
        reduced.add(pcft);
      }
 else       if (pcft.isFallbackPlugin()) {
        if (pluginGroup != null && !fallbackMap.containsKey(pluginGroup)) {
          fallbackMap.put(pluginGroup,pcft);
        }
      }
    }
 catch (    Exception e) {
      LOGGER.warning("Error evaluating aplicability of plugin",e);
    }
  }
  for (  PluginClassFileTransformer pcft : fallbackMap.values()) {
    if (pcft != null) {
      reduced.add(pcft);
    }
  }
  return reduced;
}
