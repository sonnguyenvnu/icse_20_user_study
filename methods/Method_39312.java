/** 
 * Adds message data and attachments.
 * @param emailWithData {@link Email} with data
 * @param msgToSet      {@link MimeMessage} to set data into.
 * @throws MessagingException if there is a failure.
 */
private void addBodyData(final Email emailWithData,final MimeMessage msgToSet) throws MessagingException {
  final List<EmailMessage> messages=emailWithData.messages();
  final int totalMessages=messages.size();
  final List<EmailAttachment<? extends DataSource>> attachments=new ArrayList<>(emailWithData.attachments());
  if (attachments.isEmpty() && totalMessages == 1) {
    setContent(messages.get(0),msgToSet);
  }
 else {
    final MimeMultipart multipart=new MimeMultipart();
    final MimeMultipart msgMultipart=new MimeMultipart(ALTERNATIVE);
    multipart.addBodyPart(getBaseBodyPart(msgMultipart));
    for (    final EmailMessage emailMessage : messages) {
      msgMultipart.addBodyPart(getBodyPart(emailMessage,attachments));
    }
    addAnyAttachments(attachments,multipart);
    msgToSet.setContent(multipart);
  }
}
