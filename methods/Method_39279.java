/** 
 * Adds attached  {@link ReceivedEmail}s.
 * @param emails {@link List} of {@link ReceivedEmail}s to attach.
 */
public ReceivedEmail attachedMessages(final List<ReceivedEmail> emails){
  attachedMessages.addAll(emails);
  return this;
}
