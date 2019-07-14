/** 
 * Collect error messages from the stack trace
 * @param throwable Throwable instance from which the message should be build
 * @param message   the message to which the error message will be appended
 * @return a String containing the composed messages
 */
private String collectMessages(Throwable throwable,String message){
  if (throwable != null) {
    message+="\n" + throwable.getMessage();
    return collectMessages(throwable.getCause(),message);
  }
  return message;
}
