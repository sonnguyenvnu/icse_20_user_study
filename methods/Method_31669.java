/** 
 * Scans the FileSystem for resources under the specified location, starting with the specified prefix and ending with the specified suffix.
 * @param location The location in the filesystem to start searching. Subdirectories are also searched.
 * @return The resources that were found.
 */
public Collection<LoadableResource> scanForResources(Location location){
  String path=location.getPath();
  LOG.debug("Scanning for filesystem resources at '" + path + "'");
  File dir=new File(path);
  if (!dir.exists()) {
    LOG.warn("Skipping filesystem location:" + path + " (not found)");
    return Collections.emptyList();
  }
  if (!dir.canRead()) {
    LOG.warn("Skipping filesystem location:" + path + " (not readable)");
    return Collections.emptyList();
  }
  if (!dir.isDirectory()) {
    LOG.warn("Skipping filesystem location:" + path + " (not a directory)");
    return Collections.emptyList();
  }
  Set<LoadableResource> resources=new TreeSet<>();
  for (  String resourceName : findResourceNamesFromFileSystem(path,new File(path))) {
    resources.add(new FileSystemResource(location,resourceName,encoding));
    LOG.debug("Found filesystem resource: " + resourceName);
  }
  return resources;
}
