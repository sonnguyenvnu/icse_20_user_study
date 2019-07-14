/** 
 * Sets target message for embedded attachments.
 * @param emailMessage target {@link EmailMessage}.
 */
public EmailAttachment<T> setEmbeddedMessage(final EmailMessage emailMessage){
  targetMessage=emailMessage;
  return this;
}
