public NamingMaintainService namingMaintainServiceInstance(){
  if (null != namingMaintainService) {
    return namingMaintainService;
  }
  try {
    namingMaintainService=NamingMaintainFactory.createMaintainService(getNacosProperties());
  }
 catch (  Exception e) {
    log.error("create naming service error!properties={},e=,",this,e);
    return null;
  }
  return namingMaintainService;
}
