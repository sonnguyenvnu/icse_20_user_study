/** 
 * Is regulation effective boolean.
 * @param appName the app name
 * @return the boolean
 */
public static boolean isRegulationEffective(String appName){
  FaultToleranceConfig config=getConfig(appName);
  return config.isRegulationEffective();
}
