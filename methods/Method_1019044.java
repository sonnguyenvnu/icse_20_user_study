/** 
 * Setup plugins collection.
 */
private void initialize(){
  try {
    Collection<Object> objects=manager.loadPlugins(PluginSources.jarSource(PLUGIN_DIR.toURI()));
    plugins=objects.stream().map(o -> (PluginBase)o).collect(Collectors.toSet());
    if (plugins.size() > 0)     Logging.info("Loaded " + plugins.size() + " plugins");
    for (    PluginBase p : plugins)     Logging.info(String.format("Plugin %s %s",p.name(),p.version()),1);
    for (    PluginBase p : plugins)     try {
      p.onLoad();
    }
 catch (    Exception e) {
      Logging.error(String.format("Plugin '%s' threw an exception in 'onLoad'",p.name()));
      Logging.error(e);
    }
  }
 catch (  Throwable e) {
    Logging.error("Failed to load plugins from 'plugins' directory.");
    Logging.error(e,false);
    plugins=Collections.emptySet();
  }
}
