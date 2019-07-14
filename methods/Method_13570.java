@Bean @ConditionalOnBean(AutoServiceRegistrationProperties.class) public NacosRegistration nacosRegistration(NacosDiscoveryProperties nacosDiscoveryProperties,ApplicationContext context){
  return new NacosRegistration(nacosDiscoveryProperties,context);
}
