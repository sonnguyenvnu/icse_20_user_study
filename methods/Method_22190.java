/** 
 * @return true if the current process is the process running the SenderService.NB this assumes that your SenderService is configured to used the default ':acra' process.
 */
public static boolean isACRASenderServiceProcess(){
  final String processName=getCurrentProcessName();
  if (ACRA.DEV_LOGGING)   log.d(LOG_TAG,"ACRA processName='" + processName + '\'');
  return processName != null && processName.endsWith(ACRA_PRIVATE_PROCESS_NAME);
}
