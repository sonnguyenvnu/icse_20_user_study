/** 
 * Disables this manager by restoring a  {@code null} OnSchedule hook.
 */
public void disable(){
  RxJavaPlugins.setScheduleHandler(null);
}
