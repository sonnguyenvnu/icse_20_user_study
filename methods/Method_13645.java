@Override public List<ServiceInstance> getInstances(String serviceId){
  try {
    List<Host> hosts=NamingService.getHosts(serviceId);
    return hostToServiceInstanceList(hosts,serviceId);
  }
 catch (  Exception e) {
    throw new RuntimeException("Can not get hosts from ans server. serviceId: " + serviceId,e);
  }
}
