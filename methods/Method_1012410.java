/** 
 * ??????
 */
@GetMapping("/instances") public Map<String,List<ServiceInstance>> instances(){
  Map<String,List<ServiceInstance>> instances=new HashMap<>(16);
  List<String> services=discoveryClient.getServices();
  services.forEach(s -> {
    List<ServiceInstance> list=discoveryClient.getInstances(s);
    instances.put(s,list);
  }
);
  return instances;
}
