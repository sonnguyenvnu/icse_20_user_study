private static ServiceInstance hostToServiceInstance(Instance instance,String serviceId){
  NacosServiceInstance nacosServiceInstance=new NacosServiceInstance();
  nacosServiceInstance.setHost(instance.getIp());
  nacosServiceInstance.setPort(instance.getPort());
  nacosServiceInstance.setServiceId(serviceId);
  Map<String,String> metadata=new HashMap<>();
  metadata.put("nacos.instanceId",instance.getInstanceId());
  metadata.put("nacos.weight",instance.getWeight() + "");
  metadata.put("nacos.healthy",instance.isHealthy() + "");
  metadata.put("nacos.cluster",instance.getClusterName() + "");
  metadata.putAll(instance.getMetadata());
  nacosServiceInstance.setMetadata(metadata);
  if (metadata.containsKey("secure")) {
    boolean secure=Boolean.parseBoolean(metadata.get("secure"));
    nacosServiceInstance.setSecure(secure);
  }
  return nacosServiceInstance;
}
