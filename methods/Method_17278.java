/** 
 * Returns a if the cache definition is found in the external settings file.
 * @param cacheName the name of the cache
 * @return {@code true} if a definition exists
 */
public boolean isDefinedExternally(String cacheName){
  return TypesafeConfigurator.cacheNames(rootConfig).contains(cacheName);
}
