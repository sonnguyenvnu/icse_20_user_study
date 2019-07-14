/** 
 * Adds multiple messages.
 * @param msgsToAdd {@link List} of {@link EmailMessage}s to add.
 * @return this
 */
public T message(final List<EmailMessage> msgsToAdd){
  messages.addAll(msgsToAdd);
  return _this();
}
