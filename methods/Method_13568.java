/** 
 * @return nacos discovery endpoint
 */
@ReadOperation public Map<String,Object> nacosDiscovery(){
  Map<String,Object> result=new HashMap<>();
  result.put("NacosDiscoveryProperties",nacosDiscoveryProperties);
  NamingService namingService=nacosDiscoveryProperties.namingServiceInstance();
  List<ServiceInfo> subscribe=Collections.emptyList();
  try {
    subscribe=namingService.getSubscribeServices();
  }
 catch (  Exception e) {
    log.error("get subscribe services from nacos fail,",e);
  }
  result.put("subscribe",subscribe);
  return result;
}
