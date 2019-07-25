/** 
 * Create a new instance of the plugin.
 * @param plugin plugin class
 * @return new instance or null if instantiation fail.
 */
protected Object instantiate(Class<Object> plugin){
  try {
    return plugin.newInstance();
  }
 catch (  InstantiationException e) {
    LOGGER.error("Error instantiating plugin: " + plugin.getClass().getName(),e);
  }
catch (  IllegalAccessException e) {
    LOGGER.error("Plugin: " + plugin.getClass().getName() + " does not contain public no param constructor",e);
  }
  return null;
}
