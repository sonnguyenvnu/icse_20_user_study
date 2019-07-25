/** 
 * Init plugin and resolve  version.
 * @param undertowVersion the undertow version
 * @param appClassLoader the app class loader
 */
private void init(String undertowVersion,ClassLoader appClassLoader){
  LOGGER.info("Undertow plugin initialized - Undertow version '{}'",undertowVersion);
  this.undertowVersion=undertowVersion;
}
