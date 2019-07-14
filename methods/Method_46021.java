/** 
 * Gets least window exception rate multiple.
 * @param appName the app name
 * @return the least window exception rate multiple
 */
public static double getLeastWindowExceptionRateMultiple(String appName){
  FaultToleranceConfig config=getConfig(appName);
  return config.getLeastWindowExceptionRateMultiple();
}
