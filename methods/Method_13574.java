public NamingService namingServiceInstance(){
  if (null != namingService) {
    return namingService;
  }
  try {
    namingService=NacosFactory.createNamingService(getNacosProperties());
  }
 catch (  Exception e) {
    log.error("create naming service error!properties={},e=,",this,e);
    return null;
  }
  return namingService;
}
