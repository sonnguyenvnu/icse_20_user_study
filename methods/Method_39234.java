/** 
 * Sets target  {@link EmailMessage}.
 * @param targetMessage Target {@link EmailMessage}.
 * @return this
 */
public EmailAttachmentBuilder embeddedMessage(final EmailMessage targetMessage){
  this.targetMessage=targetMessage;
  return this;
}
