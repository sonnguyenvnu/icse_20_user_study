/** 
 * Get an instance of  {@link HystrixCollapserProperties} with the given factory {@link HystrixPropertiesStrategy} implementation for each {@link HystrixCollapserKey} instance.
 * @param key Pass-thru to  {@link HystrixPropertiesStrategy#getCollapserProperties} implementation.
 * @param builder Pass-thru to  {@link HystrixPropertiesStrategy#getCollapserProperties} implementation.
 * @return {@link HystrixCollapserProperties} instance
 */
public static HystrixCollapserProperties getCollapserProperties(HystrixCollapserKey key,HystrixCollapserProperties.Setter builder){
  HystrixPropertiesStrategy hystrixPropertiesStrategy=HystrixPlugins.getInstance().getPropertiesStrategy();
  String cacheKey=hystrixPropertiesStrategy.getCollapserPropertiesCacheKey(key,builder);
  if (cacheKey != null) {
    HystrixCollapserProperties properties=collapserProperties.get(cacheKey);
    if (properties != null) {
      return properties;
    }
 else {
      if (builder == null) {
        builder=HystrixCollapserProperties.Setter();
      }
      properties=hystrixPropertiesStrategy.getCollapserProperties(key,builder);
      HystrixCollapserProperties existing=collapserProperties.putIfAbsent(cacheKey,properties);
      if (existing == null) {
        return properties;
      }
 else {
        return existing;
      }
    }
  }
 else {
    return hystrixPropertiesStrategy.getCollapserProperties(key,builder);
  }
}
