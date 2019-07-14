@Nullable static URL findURL(Class<?> context,String resource,boolean bundledWithLWJGL){
  URL url=null;
  if (bundledWithLWJGL) {
    String bundledResource=Platform.mapLibraryPathBundled(resource);
    if (!bundledResource.equals(resource)) {
      url=context.getClassLoader().getResource(bundledResource);
    }
  }
  return url == null ? context.getClassLoader().getResource(resource) : url;
}
