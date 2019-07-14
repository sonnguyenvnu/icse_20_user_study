/** 
 * Lookups resource manager for provided type. Throws an exception if provider doesn't exists.
 */
protected <E>JtxResourceManager<E> lookupResourceManager(final Class<E> resourceType){
  JtxResourceManager<E> resourceManager=this.resourceManagers.get(resourceType);
  if (resourceManager == null) {
    throw new JtxException("No registered resource manager for resource type: " + resourceType.getSimpleName());
  }
  return resourceManager;
}
