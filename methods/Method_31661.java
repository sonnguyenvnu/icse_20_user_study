public Set<String> findResourceNames(String location,URL locationUrl){
  JarFile jarFile;
  try {
    jarFile=getJarFromUrl(locationUrl);
  }
 catch (  IOException e) {
    LOG.warn("Unable to determine jar from url (" + locationUrl + "): " + e.getMessage());
    return Collections.emptySet();
  }
  try {
    String prefix=jarFile.getName().toLowerCase().endsWith(".war") ? "WEB-INF/classes/" : "";
    return findResourceNamesFromJarFile(jarFile,prefix,location);
  }
  finally {
    try {
      jarFile.close();
    }
 catch (    IOException e) {
    }
  }
}
