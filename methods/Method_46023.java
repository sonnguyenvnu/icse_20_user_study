/** 
 * Is degrade effective boolean.
 * @param appName the app name
 * @return the boolean
 */
public static boolean isDegradeEffective(String appName){
  FaultToleranceConfig config=getConfig(appName);
  return config.isDegradeEffective();
}
