/** 
 * Gets degrade max ip count.
 * @param appName the app name
 * @return the degrade max ip count
 */
public static int getDegradeMaxIpCount(String appName){
  FaultToleranceConfig config=getConfig(appName);
  return config.getDegradeMaxIpCount();
}
