/** 
 * ????????OnRunning?
 * @return ApplicationId
 */
public static String getApplicationId(){
  return Optional.ofNullable(APPLICATION_ID_WHEN_RUNNING).orElse("unsuitable call this");
}
