/** 
 * Register a general log
 * @param logFileName   logFileName
 * @param rollingPolicy rollingPolicy
 * @param logReserveDay logReserveDay
 */
public static void register(String logFileName,String rollingPolicy,String logReserveDay){
  if (StringUtils.isBlank(logFileName)) {
    return;
  }
  if (commonReporterAsyncManager.isAppenderAndEncoderExist(logFileName)) {
    SelfLog.warn(logFileName + " has existed in CommonTracerManager");
    return;
  }
  TraceAppender traceAppender=LoadTestAwareAppender.createLoadTestAwareTimedRollingFileAppender(logFileName,rollingPolicy,logReserveDay);
  commonReporterAsyncManager.addAppender(logFileName,traceAppender,commonSpanEncoder);
}
