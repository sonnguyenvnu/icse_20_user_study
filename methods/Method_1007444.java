/** 
 * Init plugin and resolve major version.
 * @param version tomcat version string
 * @param classLoader the class loader
 */
private void init(String version,ClassLoader appClassLoader){
  if (appClassLoader.getClass().getName().equals(GLASSFISH_WEBAPP_CLASS_LOADER)) {
    LOGGER.info("Tomcat plugin initialized - GlassFish embedded Tomcat version '{}'",version);
  }
 else {
    LOGGER.info("Tomcat plugin initialized - Tomcat version '{}'",version);
  }
  tomcatMajorVersion=resolveTomcatMajorVersion(version);
}
