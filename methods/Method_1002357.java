public <T>Invoker<T> select(List<Invoker<T>> invokers,URL url,Invocation invocation) throws RpcException {
  CompensableBeanFactory beanFactory=CompensableBeanRegistry.getInstance().getBeanFactory();
  RemoteCoordinatorRegistry participantRegistry=RemoteCoordinatorRegistry.getInstance();
  CompensableManager compensableManager=beanFactory.getCompensableManager();
  if (invokers == null || invokers.isEmpty()) {
    throw new RpcException("No invoker is found!");
  }
  CompensableTransactionImpl compensable=(CompensableTransactionImpl)compensableManager.getCompensableTransactionQuietly();
  List<XAResourceArchive> participantList=compensable == null ? null : compensable.getParticipantArchiveList();
  if (participantList == null || participantList.isEmpty()) {
    return this.fireChooseInvoker(invokers,url,invocation);
  }
  RpcException rpcException=null;
  for (int i=0; invokers != null && i < invokers.size(); i++) {
    Invoker<T> invoker=invokers.get(i);
    URL invokerUrl=invoker.getUrl();
    RemoteAddr remoteAddr=new RemoteAddr();
    remoteAddr.setServerHost(invokerUrl.getHost());
    remoteAddr.setServerPort(invokerUrl.getPort());
    if (participantRegistry.containsRemoteNode(remoteAddr) == false) {
      this.initializeRemoteParticipantIdentifier(remoteAddr);
    }
    RemoteNode remoteNode=participantRegistry.getRemoteNode(remoteAddr);
    if (remoteNode == null || StringUtils.isBlank(remoteNode.getServiceKey())) {
      if (rpcException == null) {
        rpcException=new RpcException("Cannot get application name of remote node!");
      }
 else {
        logger.warn("Cannot get application name of remote node({})!",remoteAddr);
      }
    }
    String application=remoteNode.getServiceKey();
    XAResourceDescriptor participant=compensable.getRemoteCoordinator(application);
    if (participant == null) {
      continue;
    }
    RemoteAddr expectAddr=CommonUtils.getRemoteAddr(participant.getIdentifier());
    if (remoteAddr.equals(expectAddr)) {
      if (invoker.isAvailable()) {
        return invoker;
      }
      throw new RpcException("The instance has been enlisted is currently unavailable.");
    }
    rpcException=new RpcException("There is already an instance of the same application being enlisted.");
  }
  if (rpcException != null) {
    throw rpcException;
  }
 else {
    return this.fireChooseInvoker(invokers,url,invocation);
  }
}
