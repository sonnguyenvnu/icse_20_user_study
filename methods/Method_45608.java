/** 
 * ????????
 * @param alias ??
 * @return DynamicManager ??
 */
public static synchronized DynamicConfigManager getDynamicManager(String appName,String alias){
  if (ALL_DYNAMICS.size() > 3) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("Size of dynamic manager is greater than 3, Please check it!");
    }
  }
  try {
    DynamicConfigManager registry=ALL_DYNAMICS.get(alias);
    if (registry == null) {
      ExtensionClass<DynamicConfigManager> ext=ExtensionLoaderFactory.getExtensionLoader(DynamicConfigManager.class).getExtensionClass(alias);
      if (ext == null) {
        throw ExceptionUtils.buildRuntime("dynamic",alias,"Unsupported alias of dynamic config !");
      }
      registry=ext.getExtInstance(new Class[]{String.class},new Object[]{appName});
      ALL_DYNAMICS.put(alias,registry);
    }
    return registry;
  }
 catch (  SofaRpcRuntimeException e) {
    throw e;
  }
catch (  Throwable e) {
    throw new SofaRpcRuntimeException(e.getMessage(),e);
  }
}
