/** 
 * Unregisters the loader asynchronously in EDT. Before the unregistration we unload all contributors which have remained loaded at that moment
 */
public void unregister(@NotNull final PluginLoader loader){
synchronized (myLoadersDeltaLock) {
    LOG.debug("Unregistering the " + loader);
    myLoaderDelta.unload(Collections.singleton(loader));
    scheduleUpdate();
  }
}
