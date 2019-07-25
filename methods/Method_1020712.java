/** 
 * Enables this manager by replacing any existing OnSchedule hook in RxJavaPlugins.
 */
public void enable(){
  RxJavaPlugins.setScheduleHandler(this);
}
