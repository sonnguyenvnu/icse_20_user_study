public XAResourceDescriptor deserialize(String identifier){
  XAResourceDescriptor resourceDescriptor=this.resourceDeserializer.deserialize(identifier);
  if (resourceDescriptor != null) {
    return resourceDescriptor;
  }
  Matcher matcher=pattern.matcher(identifier);
  if (matcher.find()) {
    RemoteCoordinatorRegistry registry=RemoteCoordinatorRegistry.getInstance();
    String application=CommonUtils.getApplication(identifier);
    RemoteCoordinator participant=registry.getParticipant(application);
    if (participant == null) {
      RemoteAddr remoteAddr=CommonUtils.getRemoteAddr(identifier);
      RemoteNode remoteNode=CommonUtils.getRemoteNode(identifier);
      this.initializeRemoteParticipantIfNecessary(application);
      registry.putRemoteNode(remoteAddr,remoteNode);
    }
    RemoteResourceDescriptor descriptor=new RemoteResourceDescriptor();
    descriptor.setIdentifier(identifier);
    descriptor.setDelegate(registry.getParticipant(application));
    return descriptor;
  }
 else {
    logger.error("can not find a matching xa-resource(identifier= {})!",identifier);
    return null;
  }
}
