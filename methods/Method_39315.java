/** 
 * Creates attachment body part. Handles regular and inline attachments.
 * @param attachment Body part {@link EmailAttachment}.
 * @return {@link MimeBodyPart} which represents body part attachment.
 * @throws MessagingException if there is a failure.
 */
protected MimeBodyPart createAttachmentBodyPart(final EmailAttachment<? extends DataSource> attachment) throws MessagingException {
  final MimeBodyPart part=new MimeBodyPart();
  final String attachmentName=attachment.getEncodedName();
  if (attachmentName != null) {
    part.setFileName(attachmentName);
  }
  part.setDataHandler(new DataHandler(attachment.getDataSource()));
  if (attachment.getContentId() != null) {
    part.setContentID(StringPool.LEFT_CHEV + attachment.getContentId() + StringPool.RIGHT_CHEV);
  }
  if (attachment.isInline()) {
    part.setDisposition(INLINE);
  }
  return part;
}
