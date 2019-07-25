/** 
 * Get the list of resources accessible to a ModuleReader. From the documentation for ModuleReader#list(): "Whether the stream of elements includes names corresponding to directories in the module is module reader specific. In lazy implementations then an IOException may be thrown when using the stream to list the module contents. If this occurs then the IOException will be wrapped in an java.io.UncheckedIOException and thrown from the method that caused the access to be attempted. SecurityException may also be thrown when using the stream to list the module contents and access is denied by the security manager."
 * @return A list of the paths of resources in the module.
 * @throws SecurityException If the module cannot be accessed.
 */
public List<String> list() throws SecurityException {
  if (collectorsToList == null) {
    throw new IllegalArgumentException("Could not call Collectors.toList()");
  }
  final Object resourcesStream=ReflectionUtils.invokeMethod(moduleReader,"list",true);
  if (resourcesStream == null) {
    throw new IllegalArgumentException("Could not call moduleReader.list()");
  }
  final Object resourcesList=ReflectionUtils.invokeMethod(resourcesStream,"collect",collectorClass,collectorsToList,true);
  if (resourcesList == null) {
    throw new IllegalArgumentException("Could not call moduleReader.list().collect(Collectors.toList())");
  }
  @SuppressWarnings("unchecked") final List<String> resourcesListTyped=(List<String>)resourcesList;
  return resourcesListTyped;
}
