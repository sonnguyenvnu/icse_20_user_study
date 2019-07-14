/** 
 * Returns a resource using parent-first or parent-last strategy.
 */
@Override public URL getResource(final String resourceName){
  URL url=null;
  Loading loading=resolveResourceLoading(parentFirst,resourceName);
  if (parentFirst) {
    if (loading.withParent) {
      url=parentClassLoader.getResource(resourceName);
    }
    if (url == null) {
      if (loading.withLoader) {
        url=this.findResource(resourceName);
      }
    }
  }
 else {
    if (loading.withLoader) {
      url=this.findResource(resourceName);
    }
    if (url == null) {
      if (loading.withParent) {
        url=parentClassLoader.getResource(resourceName);
      }
    }
  }
  return url;
}
