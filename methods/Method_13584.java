@Override public void setStatus(Registration registration,String status){
  if (!status.equalsIgnoreCase("UP") && !status.equalsIgnoreCase("DOWN")) {
    log.warn("can't support status {},please choose UP or DOWN",status);
    return;
  }
  String serviceId=registration.getServiceId();
  Instance instance=getNacosInstanceFromRegistration(registration);
  if (status.equalsIgnoreCase("DOWN")) {
    instance.setEnabled(false);
  }
 else {
    instance.setEnabled(true);
  }
  try {
    nacosDiscoveryProperties.namingMaintainServiceInstance().updateInstance(serviceId,instance);
  }
 catch (  Exception e) {
    throw new RuntimeException("update nacos instance status fail",e);
  }
}
