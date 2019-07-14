/** 
 * Adds  {@link EmailAttachment}s.
 * @param attachments {@link List} of {@link EmailAttachment}s to add.
 * @return this
 */
protected T storeAttachments(final List<EmailAttachment<? extends DataSource>> attachments){
  this.attachments.addAll(attachments);
  return _this();
}
