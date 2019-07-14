/** 
 * Adds attached  {@link ReceivedEmail}.
 * @param email {@link ReceivedEmail} to attach.
 * @return this
 */
public ReceivedEmail attachedMessage(final ReceivedEmail email){
  attachedMessages.add(email);
  return this;
}
