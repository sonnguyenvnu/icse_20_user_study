/** 
 * Copies the configuration and overlays it on top of the default settings. 
 */
@SuppressWarnings("PMD.AccessorMethodGeneration") private <K,V>CaffeineConfiguration<K,V> resolveConfigurationFor(Configuration<K,V> configuration){
  if (configuration instanceof CaffeineConfiguration<?,?>) {
    return new CaffeineConfiguration<>((CaffeineConfiguration<K,V>)configuration);
  }
  CaffeineConfiguration<K,V> template=TypesafeConfigurator.defaults(rootConfig);
  if (configuration instanceof CompleteConfiguration<?,?>) {
    CompleteConfiguration<K,V> complete=(CompleteConfiguration<K,V>)configuration;
    template.setReadThrough(complete.isReadThrough());
    template.setWriteThrough(complete.isWriteThrough());
    template.setManagementEnabled(complete.isManagementEnabled());
    template.setStatisticsEnabled(complete.isStatisticsEnabled());
    template.getCacheEntryListenerConfigurations().forEach(template::removeCacheEntryListenerConfiguration);
    complete.getCacheEntryListenerConfigurations().forEach(template::addCacheEntryListenerConfiguration);
    template.setCacheLoaderFactory(complete.getCacheLoaderFactory());
    template.setCacheWriterFactory(complete.getCacheWriterFactory());
    template.setExpiryPolicyFactory(complete.getExpiryPolicyFactory());
  }
  template.setTypes(configuration.getKeyType(),configuration.getValueType());
  template.setStoreByValue(configuration.isStoreByValue());
  return template;
}
