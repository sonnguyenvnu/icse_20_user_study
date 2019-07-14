/** 
 * Attaches the embedded attachment: Content ID will be set if missing from attachment's file name.
 * @param builder {@link EmailAttachmentBuilder}
 * @return this
 * @see #embeddedAttachment(EmailAttachment)
 */
public T embeddedAttachment(final EmailAttachmentBuilder builder){
  builder.setContentIdFromNameIfMissing();
  builder.inline(true);
  return embeddedAttachment(builder.buildByteArrayDataSource());
}
