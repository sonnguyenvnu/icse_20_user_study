public XAResourceDescriptor deserialize(String identifier){
  XAResourceDescriptor resourceDescriptor=this.resourceDeserializer.deserialize(identifier);
  if (resourceDescriptor != null) {
    return resourceDescriptor;
  }
  Matcher matcher=pattern.matcher(identifier);
  if (matcher.find() == false) {
    logger.error("can not find a matching xa-resource(identifier= {})!",identifier);
    return null;
  }
  RemoteCoordinatorRegistry registry=RemoteCoordinatorRegistry.getInstance();
  String application=CommonUtils.getApplication(identifier);
  if (registry.containsParticipant(application) == false) {
    SpringCloudCoordinator springCloudCoordinator=new SpringCloudCoordinator();
    springCloudCoordinator.setIdentifier(identifier);
    springCloudCoordinator.setEnvironment(this.environment);
    springCloudCoordinator.setStatefully(this.statefully);
    RemoteCoordinator participant=(RemoteCoordinator)Proxy.newProxyInstance(SpringCloudCoordinator.class.getClassLoader(),new Class[]{RemoteCoordinator.class},springCloudCoordinator);
    RemoteAddr remoteAddr=CommonUtils.getRemoteAddr(identifier);
    RemoteNode remoteNode=CommonUtils.getRemoteNode(identifier);
    registry.putParticipant(application,participant);
    registry.putRemoteNode(remoteAddr,remoteNode);
  }
  RemoteResourceDescriptor descriptor=new RemoteResourceDescriptor();
  descriptor.setIdentifier(identifier);
  descriptor.setDelegate(registry.getParticipant(application));
  return descriptor;
}
