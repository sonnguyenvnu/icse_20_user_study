/** 
 * Helper method that is called if no config has been explicitly configured.
 */
protected FastJsonConfig locateConfigProvider(Class<?> type,MediaType mediaType){
  if (providers != null) {
    ContextResolver<FastJsonConfig> resolver=providers.getContextResolver(FastJsonConfig.class,mediaType);
    if (resolver == null) {
      resolver=providers.getContextResolver(FastJsonConfig.class,null);
    }
    if (resolver != null) {
      return resolver.getContext(type);
    }
  }
  return fastJsonConfig;
}
