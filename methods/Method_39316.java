/** 
 * Filters out the  {@link List} of embedded {@link EmailAttachment}s for given  {@link EmailMessage}. This will remove the embedded attachments from the  {@link List} and return them in a new {@link List}.
 * @param attachments  {@link List} of attachments to search for in emailMessage.
 * @param emailMessage {@link EmailMessage} to see if attachment is embedded into.
 * @return {@link List} of embedded {@link EmailAttachment}s; otherwise, returns empty  {@link List}.
 */
protected List<EmailAttachment<? extends DataSource>> filterEmbeddedAttachments(final List<EmailAttachment<? extends DataSource>> attachments,final EmailMessage emailMessage){
  final List<EmailAttachment<? extends DataSource>> embeddedAttachments=new ArrayList<>();
  if (attachments == null || attachments.isEmpty() || emailMessage == null) {
    return embeddedAttachments;
  }
  final Iterator<EmailAttachment<? extends DataSource>> iterator=attachments.iterator();
  while (iterator.hasNext()) {
    final EmailAttachment<? extends DataSource> emailAttachment=iterator.next();
    if (emailAttachment.isEmbeddedInto(emailMessage)) {
      embeddedAttachments.add(emailAttachment);
      iterator.remove();
    }
  }
  return embeddedAttachments;
}
