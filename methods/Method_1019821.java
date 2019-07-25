/** 
 * Register the Appender being monitored
 * @param traceAppender
 */
public static void watch(TraceAppender traceAppender){
  watchedAppenders.add(traceAppender);
}
