/** 
 * Returns a newly created cache instance if a definition is found in the external settings file.
 * @param cacheName the name of the cache
 * @return a new cache instance or null if the named cache is not defined in the settings file
 */
public @Nullable <K,V>CacheProxy<K,V> tryToCreateFromExternalSettings(String cacheName){
  Optional<CaffeineConfiguration<K,V>> configuration=TypesafeConfigurator.from(rootConfig,cacheName);
  return configuration.isPresent() ? createCache(cacheName,configuration.get()) : null;
}
