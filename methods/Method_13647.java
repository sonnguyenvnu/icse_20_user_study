private static List<ServiceInstance> hostToServiceInstanceList(List<Host> hosts,String serviceId){
  List<ServiceInstance> result=new ArrayList<ServiceInstance>(hosts.size());
  for (  Host host : hosts) {
    result.add(hostToServiceInstance(host,serviceId));
  }
  return result;
}
