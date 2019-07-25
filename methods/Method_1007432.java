/** 
 * Do the reload using Log4j2 configurator.
 * @param uri the configuration file uri
 */
protected void reload(URI uri){
  try {
    IOUtils.toByteArray(uri);
  }
 catch (  Exception e) {
    LOGGER.warning("Unable to open Log4j2 configuration file {}, is it deleted?",uri);
    return;
  }
  try {
    Class<?> logManagerClass=appClassLoader.loadClass("org.apache.logging.log4j.LogManager");
    Class<?> contextClass=appClassLoader.loadClass("org.apache.logging.log4j.core.LoggerContext");
    Object context=logManagerClass.getDeclaredMethod("getContext",Boolean.TYPE).invoke(logManagerClass,true);
    contextClass.getDeclaredMethod("setConfigLocation",URI.class).invoke(context,uri);
    LOGGER.reload("Log4j2 configuration reloaded from uri '{}'.",uri);
  }
 catch (  Exception e) {
    LOGGER.error("Unable to reload {} with Log4j2",e,uri);
  }
}
