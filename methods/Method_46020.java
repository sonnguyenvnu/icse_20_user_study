/** 
 * Gets least invoke count.
 * @param appName the app name
 * @return the least invoke count
 */
public static long getLeastCallCount(String appName){
  FaultToleranceConfig config=getConfig(appName);
  return config.getLeastCallCount();
}
