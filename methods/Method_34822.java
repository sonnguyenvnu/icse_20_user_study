private <T>T getPluginImplementation(Class<T> pluginClass){
  T p=getPluginImplementationViaProperties(pluginClass,dynamicProperties);
  if (p != null)   return p;
  return findService(pluginClass,classLoader);
}
