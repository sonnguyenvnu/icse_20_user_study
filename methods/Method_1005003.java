@Override public void initialise(final Properties properties){
  String configFile=properties.getProperty(CacheProperties.CACHE_CONFIG_FILE);
  manager=CompositeCacheManager.getUnconfiguredInstance();
  if (null != configFile) {
    try {
      Properties cacheProperties=readProperties(configFile);
      manager.configure(cacheProperties);
      return;
    }
 catch (    final IOException e) {
      throw new IllegalArgumentException("Cannot create cache using config file " + configFile,e);
    }
  }
  LOGGER.debug("No config file configured. Using default.");
  manager.configure();
}
