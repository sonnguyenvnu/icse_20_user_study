@Override public Object getStatus(Registration registration){
  String serviceName=registration.getServiceId();
  try {
    List<Instance> instances=nacosDiscoveryProperties.namingServiceInstance().getAllInstances(serviceName);
    for (    Instance instance : instances) {
      if (instance.getIp().equalsIgnoreCase(nacosDiscoveryProperties.getIp()) && instance.getPort() == nacosDiscoveryProperties.getPort())       return instance.isEnabled() ? "UP" : "DOWN";
    }
  }
 catch (  Exception e) {
    log.error("get all instance of {} error,",serviceName,e);
  }
  return null;
}
