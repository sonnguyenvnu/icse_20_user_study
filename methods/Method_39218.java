/** 
 * Adds  {@link EmailAttachment}.
 * @param attachment {@link EmailAttachment} to add.
 * @return this
 */
protected T storeAttachment(final EmailAttachment<? extends DataSource> attachment){
  this.attachments.add(attachment);
  return _this();
}
