@Bean public NacosServiceRegistry nacosServiceRegistry(NacosDiscoveryProperties nacosDiscoveryProperties){
  return new NacosServiceRegistry(nacosDiscoveryProperties);
}
