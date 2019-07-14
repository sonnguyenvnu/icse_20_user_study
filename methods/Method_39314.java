/** 
 * @param emailMessage {@link EmailMessage} with data.
 * @param attachments  {@link List} of {@link EmailAttachment}s.
 * @return new {@link MimeBodyPart} with data from emailMessage and attachments.
 * @throws MessagingException if there is a failure.
 */
private MimeBodyPart getBodyPart(final EmailMessage emailMessage,final List<EmailAttachment<? extends DataSource>> attachments) throws MessagingException {
  final MimeBodyPart bodyPart=new MimeBodyPart();
  final List<EmailAttachment<? extends DataSource>> embeddedAttachments=filterEmbeddedAttachments(attachments,emailMessage);
  if (embeddedAttachments.isEmpty()) {
    setContent(emailMessage,bodyPart);
  }
 else {
    attachments.removeAll(embeddedAttachments);
    final MimeMultipart relatedMultipart=new MimeMultipart(RELATED);
    final MimeBodyPart messageData=new MimeBodyPart();
    setContent(emailMessage,messageData);
    relatedMultipart.addBodyPart(messageData);
    addAnyAttachments(embeddedAttachments,relatedMultipart);
    bodyPart.setContent(relatedMultipart);
  }
  return bodyPart;
}
