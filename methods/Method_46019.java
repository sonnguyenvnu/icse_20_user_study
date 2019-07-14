/** 
 * getTimeWindow
 * @param appName App name
 * @return time window
 */
public static long getTimeWindow(String appName){
  FaultToleranceConfig config=getConfig(appName);
  return config.getTimeWindow();
}
