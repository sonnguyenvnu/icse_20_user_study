/** 
 * Put app config.
 * @param appName the app name
 * @param value   the value
 */
public static void putAppConfig(String appName,FaultToleranceConfig value){
  if (appName == null) {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn("App name is null when put fault-tolerance config");
    }
    return;
  }
  if (value != null) {
    APP_CONFIGS.put(appName,value);
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,"Get a new resource, value[" + value + "]");
    }
  }
 else {
    APP_CONFIGS.remove(appName);
    if (LOGGER.isInfoEnabled(appName)) {
      LOGGER.infoWithApp(appName,"Remove a resource, key[" + appName + "]");
    }
  }
  calcEnable();
}
