/** 
 * Detects new service classes and reloads service loaders if necessary.
 */
protected void reload(){
  log.debug("Reloading services");
  if (fileSystemLoader.update()) {
    log.debug("New file systems found");
    conf.setClassLoader(this);
    FileSystemUtil.registerFileSystems(fileSystemLoader,conf);
  }
}
