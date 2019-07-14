/** 
 * Handle invalid helper data without exception details or because none was thrown.
 * @param message message to log and return
 * @return a message which will be used as content
 */
protected String handleError(final String message){
  notifier().error(formatMessage(message));
  return formatMessage(message);
}
