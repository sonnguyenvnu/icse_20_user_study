/** 
 * Requests a resource. If resource is not found, it will be created and new transaction will be started on it.
 */
public <E>E requestResource(final Class<E> resourceType){
  if (isCompleted()) {
    throw new JtxException("TX is already completed, resource are not available after commit or rollback");
  }
  if (isRollbackOnly()) {
    throw new JtxException("TX is marked as rollback only, resource are not available",rollbackCause);
  }
  if (!isNoTransaction() && !isActive()) {
    throw new JtxException("Resources are not available since TX is not active");
  }
  checkTimeout();
  E resource=lookupResource(resourceType);
  if (resource == null) {
    int maxResources=txManager.getMaxResourcesPerTransaction();
    if ((maxResources != -1) && (resources.size() >= maxResources)) {
      throw new JtxException("TX already has attached max. number of resources");
    }
    JtxResourceManager<E> resourceManager=txManager.lookupResourceManager(resourceType);
    resource=resourceManager.beginTransaction(mode,isActive());
    resources.add(new JtxResource<>(this,resourceManager,resource));
  }
  return resource;
}
