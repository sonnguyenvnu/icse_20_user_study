@Bean @ConditionalOnMissingBean @ConfigurationProperties(prefix="spring.boot.admin.discovery") public InstanceDiscoveryListener instanceDiscoveryListener(ServiceInstanceConverter serviceInstanceConverter,DiscoveryClient discoveryClient,InstanceRegistry registry,InstanceRepository repository){
  InstanceDiscoveryListener listener=new InstanceDiscoveryListener(discoveryClient,registry,repository);
  listener.setConverter(serviceInstanceConverter);
  return listener;
}
