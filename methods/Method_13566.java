@Bean @ConditionalOnMissingBean public NacosDiscoveryProperties nacosProperties(){
  return new NacosDiscoveryProperties();
}
