public static void init(List<String> classPathEntries,Problems problems){
  checkState(packageInfoCacheStorage.get() == null,"PackageInfoCache should only be initialized once per thread.");
  List<URL> classPathUrls=new ArrayList<>();
  for (  String classPathEntry : classPathEntries) {
    try {
      classPathUrls.add(new URL("file:" + classPathEntry));
    }
 catch (    MalformedURLException e) {
      problems.fatal(FatalError.CANNOT_OPEN_FILE,e.toString());
    }
  }
  URLClassLoader resourcesClassLoader=new URLClassLoader(Iterables.toArray(classPathUrls,URL.class),PackageInfoCache.class.getClassLoader());
  packageInfoCacheStorage.set(new PackageInfoCache(resourcesClassLoader,problems));
}
