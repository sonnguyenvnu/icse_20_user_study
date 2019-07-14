/** 
 * Finds all the resource names contained in this file system folder.
 * @param scanRootLocation The root location of the scan on disk.
 * @param folder           The folder to look for resources under on disk.
 * @return The resource names;
 */
@SuppressWarnings("ConstantConditions") private Set<String> findResourceNamesFromFileSystem(String scanRootLocation,File folder){
  LOG.debug("Scanning for resources in path: " + folder.getPath() + " (" + scanRootLocation + ")");
  Set<String> resourceNames=new TreeSet<>();
  File[] files=folder.listFiles();
  for (  File file : files) {
    if (file.canRead()) {
      if (file.isDirectory()) {
        if (file.isHidden()) {
          LOG.debug("Skipping hidden directory: " + file.getAbsolutePath());
        }
 else {
          resourceNames.addAll(findResourceNamesFromFileSystem(scanRootLocation,file));
        }
      }
 else {
        resourceNames.add(file.getPath());
      }
    }
  }
  return resourceNames;
}
