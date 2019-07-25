/** 
 * Patch the classloader.
 * @param classLoaderFrom  classloader to load classes from
 * @param path             path to copy
 * @param classLoaderTo    classloader to copy classes to
 * @param protectionDomain required protection in target classloader
 */
public void patch(final ClassLoader classLoaderFrom,final String pluginPath,final ClassLoader classLoaderTo,final ProtectionDomain protectionDomain){
  List<byte[]> cache=getPluginCache(classLoaderFrom,pluginPath);
  if (cache != null) {
    final ClassPool cp=new ClassPool();
    cp.appendClassPath(new LoaderClassPath(getClass().getClassLoader()));
    for (    byte[] pluginBytes : cache) {
      CtClass pluginClass=null;
      try {
        InputStream is=new ByteArrayInputStream(pluginBytes);
        pluginClass=cp.makeClass(is);
        try {
          classLoaderFrom.loadClass(pluginClass.getName());
        }
 catch (        NoClassDefFoundError e) {
          LOGGER.trace("Skipping class loading {} in classloader {} - " + "class has probably unresolvable dependency.",pluginClass.getName(),classLoaderTo);
        }
        pluginClass.toClass(classLoaderTo,protectionDomain);
      }
 catch (      CannotCompileException e) {
        LOGGER.trace("Skipping class definition {} in app classloader {} - " + "class is probably already defined.",pluginClass.getName(),classLoaderTo);
      }
catch (      NoClassDefFoundError e) {
        LOGGER.trace("Skipping class definition {} in app classloader {} - " + "class has probably unresolvable dependency.",pluginClass.getName(),classLoaderTo);
      }
catch (      Throwable e) {
        LOGGER.trace("Skipping class definition app classloader {} - " + "unknown error.",e,classLoaderTo);
      }
    }
  }
  LOGGER.debug("Classloader {} patched with plugin classes from agent classloader {}.",classLoaderTo,classLoaderFrom);
}
