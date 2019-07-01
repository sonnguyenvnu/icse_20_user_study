private CompletableFuture<StorageContainer> _XXXXX_(long scId){
  if (closed) {
    return FutureUtils.exception(new ObjectClosedException(COMPONENT_NAME));
  }
  if (containers.containsKey(scId)) {
    return FutureUtils.exception(new StorageException("StorageContainer " + scId + " already registered"));
  }
  StorageContainer newStorageContainer=scFactory.createStorageContainer(scId);
  StorageContainer oldStorageContainer=containers.putIfAbsent(scId,newStorageContainer);
  if (null != oldStorageContainer) {
    newStorageContainer.close();
    return FutureUtils.exception(new StorageException("StorageContainer " + scId + " already registered"));
  }
  log.info("Registered StorageContainer ('{}').",scId);
  return newStorageContainer.start().whenComplete((container,cause) -> {
    if (null != cause) {
      if (containers.remove(scId,newStorageContainer)) {
        log.warn("De-registered StorageContainer ('{}') when failed to start",scId,cause);
      }
 else {
        log.warn("Fail to de-register StorageContainer ('{}') when failed to start",scId,cause);
      }
    }
 else {
      log.info("Successfully started registered StorageContainer ('{}').",scId);
    }
  }
);
}