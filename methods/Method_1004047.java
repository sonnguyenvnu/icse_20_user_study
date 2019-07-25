/** 
 * Adds the given file or directory resource to the locator.
 * @param resource resource to add
 */
void add(final Resource resource){
  empty=false;
  if (resource.isDirectory()) {
    final FileResource dir=(FileResource)resource;
    super.add(new DirectorySourceFileLocator(dir.getFile(),encoding,getTabWidth()));
  }
 else {
    filesLocator.add(resource);
  }
}
