/** 
 * Adds received attachment.
 * @param part    {@link Part}.
 * @param content Content as {@link InputStream}.
 * @return this
 * @see #attachment(EmailAttachment)
 */
private ReceivedEmail addAttachment(final Part part,final InputStream content,final File attachmentStorage) throws MessagingException, IOException {
  final EmailAttachmentBuilder builder=addAttachmentInfo(part);
  builder.content(content,part.getContentType());
  if (attachmentStorage != null) {
    String name=messageId + "-" + (this.attachments().size() + 1);
    return storeAttachment(builder.buildFileDataSource(name,attachmentStorage));
  }
  return storeAttachment(builder.buildByteArrayDataSource());
}
