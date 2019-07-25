/** 
 * {@inheritDoc}
 */
@Override public Settings process(){
  createSettingsResolvers();
  checkForDeprecatedKeys();
  String classLoaderBeanName=String.valueOf(getValue(SettingsKeys.CLASS_LOADER_BEAN,SettingsDefaults.CLASS_LOADER_BEAN));
  String proxyResolverBeanName=String.valueOf(getValue(SettingsKeys.PROXY_RESOLVER_BEAN,SettingsDefaults.PROXY_RESOLVER_BEAN));
  Integer converterByDestTypeCacheMaxSize=Integer.valueOf(getValue(SettingsKeys.CONVERTER_BY_DEST_TYPE_CACHE_MAX_SIZE,SettingsDefaults.CONVERTER_BY_DEST_TYPE_CACHE_MAX_SIZE).toString());
  Integer superTypesCacheMaxSize=Integer.valueOf(getValue(SettingsKeys.SUPER_TYPE_CHECK_CACHE_MAX_SIZE,SettingsDefaults.SUPER_TYPE_CHECK_CACHE_MAX_SIZE).toString());
  Boolean useJaxbMappingEngine=Boolean.valueOf(getValue(SettingsKeys.USE_JAXB_MAPPING_ENGINE,SettingsDefaults.USE_JAXB_MAPPING_ENGINE).toString());
  return new Settings(converterByDestTypeCacheMaxSize,superTypesCacheMaxSize,classLoaderBeanName,proxyResolverBeanName,useJaxbMappingEngine);
}
