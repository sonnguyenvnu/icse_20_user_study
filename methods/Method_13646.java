private static ServiceInstance hostToServiceInstance(Host host,String serviceId){
  AnsServiceInstance ansServiceInstance=new AnsServiceInstance();
  ansServiceInstance.setHost(host.getIp());
  ansServiceInstance.setPort(host.getPort());
  ansServiceInstance.setServiceId(serviceId);
  Map<String,String> metadata=new HashMap<String,String>(5);
  metadata.put("appUseType",host.getAppUseType());
  metadata.put("site",host.getSite());
  metadata.put("unit",host.getUnit());
  metadata.put("doubleWeight","" + host.getDoubleWeight());
  metadata.put("weight","" + host.getWeight());
  ansServiceInstance.setMetadata(metadata);
  return ansServiceInstance;
}
