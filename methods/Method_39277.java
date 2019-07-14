/** 
 * Adds received attachment.
 * @param part    {@link Part}.
 * @param content Content as byte array.
 * @return this
 * @see #attachment(EmailAttachment)
 */
private ReceivedEmail addAttachment(final Part part,final byte[] content) throws MessagingException {
  final EmailAttachmentBuilder builder=addAttachmentInfo(part);
  builder.content(content,part.getContentType());
  final EmailAttachment<ByteArrayDataSource> attachment=builder.buildByteArrayDataSource();
  attachment.setSize(content.length);
  return storeAttachment(attachment);
}
