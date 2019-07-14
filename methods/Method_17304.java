/** 
 * Retrieves the names of the caches defined in the configuration resource.
 * @param config the configuration resource
 * @return the names of the configured caches
 */
public static Set<String> cacheNames(Config config){
  return config.hasPath("caffeine.jcache") ? Collections.unmodifiableSet(config.getObject("caffeine.jcache").keySet()) : Collections.emptySet();
}
