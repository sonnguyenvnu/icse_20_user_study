private static List<ServiceInstance> hostToServiceInstanceList(List<Instance> instances,String serviceId){
  List<ServiceInstance> result=new ArrayList<>(instances.size());
  for (  Instance instance : instances) {
    result.add(hostToServiceInstance(instance,serviceId));
  }
  return result;
}
