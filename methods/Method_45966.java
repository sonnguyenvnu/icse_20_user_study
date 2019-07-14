/** 
 * System.getProperty() > ???? > rpc-config.properties
 * @param appName ???
 * @param key     ???
 * @return ??
 */
private static String getStringValue0(String appName,String key){
  String ret=System.getProperty(key);
  if (StringUtils.isNotEmpty(ret)) {
    return ret;
  }
  rLock.lock();
  try {
    for (    ExternalConfigLoader configLoader : CONFIG_LOADERS) {
      ret=appName == null ? configLoader.getValue(key) : configLoader.getValue(appName,key);
      if (StringUtils.isNotEmpty(ret)) {
        return ret;
      }
    }
  }
  finally {
    rLock.unlock();
  }
  return getConfig().getProperty(key);
}
