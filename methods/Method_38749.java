/** 
 * Lookups for open resource. Returns <code>null</code> if resource not found. Only open resources can be found.
 */
protected <E>E lookupResource(final Class<E> resourceType){
  for (  JtxResource jtxResource : resources) {
    if (jtxResource.isSameTypeAsResource(resourceType)) {
      return (E)jtxResource.getResource();
    }
  }
  return null;
}
