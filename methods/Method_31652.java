/** 
 * Finds the resources names present at this location and below on the classpath starting with this prefix and ending with this suffix.
 * @return The resource names.
 */
private Set<String> findResourceNames(){
  Set<String> resourceNames=new TreeSet<>();
  List<URL> locationUrls=getLocationUrlsForPath(location);
  for (  URL locationUrl : locationUrls) {
    LOG.debug("Scanning URL: " + locationUrl.toExternalForm());
    UrlResolver urlResolver=createUrlResolver(locationUrl.getProtocol());
    URL resolvedUrl=urlResolver.toStandardJavaUrl(locationUrl);
    String protocol=resolvedUrl.getProtocol();
    ClassPathLocationScanner classPathLocationScanner=createLocationScanner(protocol);
    if (classPathLocationScanner == null) {
      String scanRoot=UrlUtils.toFilePath(resolvedUrl);
      LOG.warn("Unable to scan location: " + scanRoot + " (unsupported protocol: " + protocol + ")");
    }
 else {
      Set<String> names=resourceNameCache.get(classPathLocationScanner).get(resolvedUrl);
      if (names == null) {
        names=classPathLocationScanner.findResourceNames(location.getPath(),resolvedUrl);
        resourceNameCache.get(classPathLocationScanner).put(resolvedUrl,names);
      }
      resourceNames.addAll(names);
    }
  }
  boolean locationResolved=!locationUrls.isEmpty();
  boolean isClassPathRoot=location.isClassPath() && "".equals(location.getPath());
  if (!locationResolved || isClassPathRoot) {
    if (classLoader instanceof URLClassLoader) {
      URLClassLoader urlClassLoader=(URLClassLoader)classLoader;
      for (      URL url : urlClassLoader.getURLs()) {
        if ("file".equals(url.getProtocol()) && url.getPath().endsWith(".jar") && !url.getPath().matches(".*" + Pattern.quote("/jre/lib/") + ".*")) {
          JarFile jarFile;
          try {
            try {
              jarFile=new JarFile(url.toURI().getSchemeSpecificPart());
            }
 catch (            URISyntaxException ex) {
              jarFile=new JarFile(url.getPath().substring("file:".length()));
            }
          }
 catch (          IOException|SecurityException e) {
            LOG.warn("Skipping unloadable jar file: " + url + " (" + e.getMessage() + ")");
            continue;
          }
          try {
            Enumeration<JarEntry> entries=jarFile.entries();
            while (entries.hasMoreElements()) {
              String entryName=entries.nextElement().getName();
              if (entryName.startsWith(location.getPath())) {
                locationResolved=true;
                resourceNames.add(entryName);
              }
            }
          }
  finally {
            try {
              jarFile.close();
            }
 catch (            IOException e) {
            }
          }
        }
      }
    }
  }
  if (!locationResolved) {
    LOG.warn("Unable to resolve location " + location);
  }
  return resourceNames;
}
