/** 
 * Finds all the resource names contained in this directory within this jar file.
 * @param jarFile  The jar file.
 * @param prefix   The prefix to ignore within the jar file.
 * @param location The location to look under.
 * @return The resource names.
 */
private Set<String> findResourceNamesFromJarFile(JarFile jarFile,String prefix,String location){
  String toScan=prefix + location + (location.endsWith("/") ? "" : "/");
  Set<String> resourceNames=new TreeSet<>();
  Enumeration<JarEntry> entries=jarFile.entries();
  while (entries.hasMoreElements()) {
    String entryName=entries.nextElement().getName();
    if (entryName.startsWith(toScan)) {
      resourceNames.add(entryName.substring(prefix.length()));
    }
  }
  return resourceNames;
}
