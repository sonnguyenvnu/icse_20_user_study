@Override public List<ServiceInstance> getInstances(String serviceId){
  try {
    List<Instance> instances=discoveryProperties.namingServiceInstance().selectInstances(serviceId,true);
    return hostToServiceInstanceList(instances,serviceId);
  }
 catch (  Exception e) {
    throw new RuntimeException("Can not get hosts from nacos server. serviceId: " + serviceId,e);
  }
}
