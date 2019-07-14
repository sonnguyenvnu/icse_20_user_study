@Override public Enumeration<URL> getResources(final String resourceName) throws IOException {
  final List<URL> urls=new ArrayList<>();
  Enumeration<URL> loaderUrls=this.findResources(resourceName);
  Enumeration<URL> parentUrls=parentClassLoader.getResources(resourceName);
  Loading loading=resolveResourceLoading(parentFirst,resourceName);
  if (parentFirst) {
    if (loading.withParent) {
      while (parentUrls.hasMoreElements()) {
        urls.add(parentUrls.nextElement());
      }
    }
    if (loading.withLoader) {
      while (loaderUrls.hasMoreElements()) {
        urls.add(loaderUrls.nextElement());
      }
    }
  }
 else {
    if (loading.withLoader) {
      while (loaderUrls.hasMoreElements()) {
        urls.add(loaderUrls.nextElement());
      }
    }
    if (loading.withParent) {
      while (parentUrls.hasMoreElements()) {
        urls.add(parentUrls.nextElement());
      }
    }
  }
  return new Enumeration<URL>(){
    @Override public boolean hasMoreElements(){
      return iterator.hasNext();
    }
    @Override public URL nextElement(){
      return iterator.next();
    }
  }
;
}
