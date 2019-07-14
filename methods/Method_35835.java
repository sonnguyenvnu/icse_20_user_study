/** 
 * Handle invalid helper data with exception details in the log message.
 * @param message message to log and return
 * @param cause   which occurred during application of the helper
 * @return a message which will be used as content
 */
protected String handleError(final String message,final Throwable cause){
  notifier().error(formatMessage(message),cause);
  return formatMessage(message);
}
