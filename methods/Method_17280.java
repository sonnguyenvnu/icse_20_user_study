/** 
 * Returns a fully constructed cache based on the cache
 * @param cacheName the name of the cache
 * @param configuration the full cache definition
 * @return a newly constructed cache instance
 */
public <K,V>CacheProxy<K,V> createCache(String cacheName,Configuration<K,V> configuration){
  CaffeineConfiguration<K,V> config=resolveConfigurationFor(configuration);
  return new Builder<>(cacheName,config).build();
}
