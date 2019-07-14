private Instance getNacosInstanceFromRegistration(Registration registration){
  Instance instance=new Instance();
  instance.setIp(registration.getHost());
  instance.setPort(registration.getPort());
  instance.setWeight(nacosDiscoveryProperties.getWeight());
  instance.setClusterName(nacosDiscoveryProperties.getClusterName());
  instance.setMetadata(registration.getMetadata());
  return instance;
}
