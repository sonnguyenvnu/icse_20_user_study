private boolean register(DockerSeleniumRemoteProxy proxy){
  String containerId=proxy.getContainerId();
  ContainerStatus containerStatus=null;
  for (  Entry<ContainerCreationStatus,ContainerStatus> container : this.startedContainers.entrySet()) {
    if (Objects.equals(container.getKey().getContainerName(),containerId) || Objects.equals(container.getKey().getContainerId(),containerId)) {
      container.getValue().setProxy(Optional.of(proxy));
      containerStatus=container.getValue();
      break;
    }
  }
  if (containerStatus == null) {
    LOGGER.warn("Registered (or re-registered) a container {} {} that is not tracked by the pool, marking down.",containerId,proxy);
    proxy.markDown();
    return false;
  }
 else   if (containerStatus.isShuttingDown()) {
    LOGGER.warn("Registered (or re-registered) a container {} {} that is shutting down, marking down.",containerId,proxy);
    proxy.markDown();
    return false;
  }
 else {
    LOGGER.debug("Registered a container {} {}.",containerId,proxy);
    return true;
  }
}
