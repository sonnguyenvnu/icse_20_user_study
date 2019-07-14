/** 
 * Provide additional detail by prepending a message to the existing message. A colon is separator is automatically inserted between the messages.
 * @since 1.3
 */
public void prependMessage(String message){
  if (iMessage == null) {
    iMessage=message;
  }
 else   if (message != null) {
    iMessage=message + ": " + iMessage;
  }
}
