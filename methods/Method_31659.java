/** 
 * Finds all the resource names contained in this file system folder.
 * @param classPathRootOnDisk The location of the classpath root on disk, with a trailing slash.
 * @param scanRootLocation    The root location of the scan on the classpath, without leading or trailing slashes.
 * @param folder              The folder to look for resources under on disk.
 * @return The resource names;
 */
@SuppressWarnings("ConstantConditions") Set<String> findResourceNamesFromFileSystem(String classPathRootOnDisk,String scanRootLocation,File folder){
  LOG.debug("Scanning for resources in path: " + folder.getPath() + " (" + scanRootLocation + ")");
  Set<String> resourceNames=new TreeSet<>();
  File[] files=folder.listFiles();
  for (  File file : files) {
    if (file.canRead()) {
      if (file.isDirectory()) {
        resourceNames.addAll(findResourceNamesFromFileSystem(classPathRootOnDisk,scanRootLocation,file));
      }
 else {
        resourceNames.add(toResourceNameOnClasspath(classPathRootOnDisk,file));
      }
    }
  }
  return resourceNames;
}
