/** 
 * @param path path???/??
 */
protected synchronized void loadFromFile(String path){
  if (LOGGER.isDebugEnabled()) {
    LOGGER.debug("Loading extension of extensible {} from path: {}",interfaceName,path);
  }
  String file=StringUtils.isBlank(extensible.file()) ? interfaceName : extensible.file().trim();
  String fullFileName=path + file;
  try {
    ClassLoader classLoader=ClassLoaderUtils.getClassLoader(getClass());
    loadFromClassLoader(classLoader,fullFileName);
  }
 catch (  Throwable t) {
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error("Failed to load extension of extensible " + interfaceName + " from path:" + fullFileName,t);
    }
  }
}
