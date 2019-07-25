/** 
 * For each classloader check for autoHotswap configuration instance with hotswapper.
 */
@Init public static void init(PluginConfiguration pluginConfiguration,ClassLoader appClassLoader){
  if (appClassLoader == null) {
    LOGGER.debug("Bootstrap class loader is null, hotswapper skipped.");
    return;
  }
  LOGGER.debug("Init plugin at classLoader {}",appClassLoader);
  if (!HotswapAgent.isAutoHotswap() && !pluginConfiguration.containsPropertyFile()) {
    LOGGER.debug("ClassLoader {} does not contain hotswap-agent.properties file, hotswapper skipped.",appClassLoader);
    return;
  }
  if (!HotswapAgent.isAutoHotswap() && !pluginConfiguration.getPropertyBoolean("autoHotswap")) {
    LOGGER.debug("ClassLoader {} has autoHotswap disabled, hotswapper skipped.",appClassLoader);
    return;
  }
  String port=pluginConfiguration.getProperty("autoHotswap.port");
  HotswapperPlugin plugin=PluginManagerInvoker.callInitializePlugin(HotswapperPlugin.class,appClassLoader);
  if (plugin != null) {
    plugin.initHotswapCommand(appClassLoader,port);
  }
 else {
    LOGGER.debug("Hotswapper is disabled in {}",appClassLoader);
  }
}
