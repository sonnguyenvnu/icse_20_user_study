/** 
 * Handle invalid helper data with exception details in the log message. Also additional information regarding the issue is written in the logs.
 * @param message      message to log and return
 * @param logExclusive additional information just for the log
 * @param cause        which occured during application of the helper
 * @return a message which will be used as content
 */
protected String handleError(final String message,final String logExclusive,final Throwable cause){
  notifier().error(ERROR_PREFIX + message + " - " + logExclusive,cause);
  return formatMessage(message);
}
