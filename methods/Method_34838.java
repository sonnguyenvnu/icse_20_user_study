/** 
 * Get an instance of  {@link HystrixCommandProperties} with the given factory {@link HystrixPropertiesStrategy} implementation for each {@link HystrixCommand} instance.
 * @param key Pass-thru to  {@link HystrixPropertiesStrategy#getCommandProperties} implementation.
 * @param builder Pass-thru to  {@link HystrixPropertiesStrategy#getCommandProperties} implementation.
 * @return {@link HystrixCommandProperties} instance
 */
public static HystrixCommandProperties getCommandProperties(HystrixCommandKey key,HystrixCommandProperties.Setter builder){
  HystrixPropertiesStrategy hystrixPropertiesStrategy=HystrixPlugins.getInstance().getPropertiesStrategy();
  String cacheKey=hystrixPropertiesStrategy.getCommandPropertiesCacheKey(key,builder);
  if (cacheKey != null) {
    HystrixCommandProperties properties=commandProperties.get(cacheKey);
    if (properties != null) {
      return properties;
    }
 else {
      if (builder == null) {
        builder=HystrixCommandProperties.Setter();
      }
      properties=hystrixPropertiesStrategy.getCommandProperties(key,builder);
      HystrixCommandProperties existing=commandProperties.putIfAbsent(cacheKey,properties);
      if (existing == null) {
        return properties;
      }
 else {
        return existing;
      }
    }
  }
 else {
    return hystrixPropertiesStrategy.getCommandProperties(key,builder);
  }
}
