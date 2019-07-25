/** 
 * Looks at a system property and initialises an appropriate cache service. Adds a shutdown hook which gracefully closes the cache service if JVM is stopped. This should not be relied upon in a servlet context - use the ServletLifecycleListener located in the REST module instead
 * @param properties the cache service properties
 * @throws IllegalArgumentException if an invalid cache class is specified in the system property
 */
public static void initialise(final Properties properties){
  if (null == properties) {
    LOGGER.warn("received null properties - exiting initialise method without creating service");
    return;
  }
  final String cacheClass=properties.getProperty(CacheProperties.CACHE_SERVICE_CLASS);
  if (null == cacheClass) {
    if (null == service) {
      LOGGER.debug("No cache service class was specified in properties.");
    }
    return;
  }
  try {
    service=Class.forName(cacheClass).asSubclass(ICacheService.class).newInstance();
  }
 catch (  final InstantiationException|IllegalAccessException|ClassNotFoundException e) {
    throw new IllegalArgumentException("Failed to instantiate cache using class " + cacheClass,e);
  }
  service.initialise(properties);
  if (!shutdownHookAdded) {
    Runtime.getRuntime().addShutdownHook(new Thread(CacheServiceLoader::shutdown));
    shutdownHookAdded=true;
  }
}
