/** 
 * Embed  {@link EmailAttachment} to last message. No header is changed.
 * @param attachment {@link EmailAttachment}
 * @return this
 * @see #storeAttachment(EmailAttachment)
 */
public T embeddedAttachment(final EmailAttachment<? extends DataSource> attachment){
  storeAttachment(attachment);
  final List<EmailMessage> messages=messages();
  final int size=messages.size();
  if (size > 1) {
    final int lastMessagePos=size - 1;
    final EmailMessage lastMessage=messages.get(lastMessagePos);
    attachment.setEmbeddedMessage(lastMessage);
  }
  return _this();
}
