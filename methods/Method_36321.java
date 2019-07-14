/** 
 * Initializer for SOFA Boot Log Space Isolation
 * @param environment   Application Context Environment
 * @param runtimeLogLevelKey sofa-common-tools Runtime Log Level for every SOFABoot starters or plugin,such as logging.level.com.alipay.sofa.runtime
 */
public static void initSofaBootLogger(Environment environment,String runtimeLogLevelKey){
  String loggingPath=environment.getProperty(Constants.LOG_PATH);
  if (!StringUtils.isEmpty(loggingPath)) {
    System.setProperty(Constants.LOG_PATH,environment.getProperty(Constants.LOG_PATH));
    ReportUtil.report("Actual " + Constants.LOG_PATH + " is [ " + loggingPath + " ]");
  }
  String runtimeLogLevelValue=environment.getProperty(runtimeLogLevelKey);
  if (runtimeLogLevelValue != null) {
    System.setProperty(runtimeLogLevelKey,runtimeLogLevelValue);
  }
  String fileEncoding=environment.getProperty(Constants.LOG_ENCODING_PROP_KEY);
  if (!StringUtils.isEmpty(fileEncoding)) {
    System.setProperty(Constants.LOG_ENCODING_PROP_KEY,fileEncoding);
  }
}
