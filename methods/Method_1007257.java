/** 
 * Initialize the singleton plugin manager. <ul> <li>Create new resource watcher using WatcherFactory and start it in separate thread.</li> <li>Create new scheduler and start it in separate thread.</li> <li>Scan for plugins</li> <li>Register HotswapTransformer with the javaagent instrumentation class</li> </ul>
 * @param instrumentation javaagent instrumentation.
 */
public void init(Instrumentation instrumentation){
  this.instrumentation=instrumentation;
  ClassLoader classLoader=getClass().getClassLoader();
  classLoaderConfigurations.put(classLoader,new PluginConfiguration(classLoader));
  if (watcher == null) {
    try {
      watcher=new WatcherFactory().getWatcher();
    }
 catch (    IOException e) {
      LOGGER.debug("Unable to create default watcher.",e);
    }
  }
  watcher.run();
  if (scheduler == null) {
    scheduler=new SchedulerImpl();
  }
  scheduler.run();
  pluginRegistry.scanPlugins(getClass().getClassLoader(),PLUGIN_PACKAGE);
  LOGGER.debug("Registering transformer ");
  instrumentation.addTransformer(hotswapTransformer);
}
