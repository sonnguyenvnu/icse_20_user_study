/** 
 * Init the plugin instance for resources.
 * @param watchResources resources to watch
 */
private void init(URL[] watchResources){
  watchResourcesClassLoader.initWatchResources(watchResources,watcher);
  if (appClassLoader instanceof URLClassLoader) {
    URLClassLoaderHelper.setWatchResourceLoader((URLClassLoader)appClassLoader,watchResourcesClassLoader);
  }
 else   if (appClassLoader instanceof HotswapAgentClassLoaderExt) {
    ((HotswapAgentClassLoaderExt)appClassLoader).$$ha$setWatchResourceLoader(watchResourcesClassLoader);
  }
}
