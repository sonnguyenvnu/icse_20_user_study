/** 
 * Resolves resources.
 */
protected Loading resolveResourceLoading(final boolean parentFirstStrategy,String resourceName){
  if (matchResourcesAsPackages) {
    resourceName=StringUtil.replaceChar(resourceName,'/','.');
  }
  return resolveLoading(parentFirstStrategy,resourceName);
}
