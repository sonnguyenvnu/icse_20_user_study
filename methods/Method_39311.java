/** 
 * Sets TO, CC and BCC in msgToSet with TO, CC and BCC from emailWithData.
 * @param emailWithData {@link Email} with data
 * @param msgToSet      {@link MimeMessage} to set data into.
 * @throws MessagingException if there is a failure.
 */
private void setRecipients(final Email emailWithData,final MimeMessage msgToSet) throws MessagingException {
  final InternetAddress[] to=EmailAddress.convert(emailWithData.to());
  if (to.length > 0) {
    msgToSet.setRecipients(RecipientType.TO,to);
  }
  final InternetAddress[] cc=EmailAddress.convert(emailWithData.cc());
  if (cc.length > 0) {
    msgToSet.setRecipients(RecipientType.CC,cc);
  }
  final InternetAddress[] bcc=EmailAddress.convert(emailWithData.bcc());
  if (bcc.length > 0) {
    msgToSet.setRecipients(RecipientType.BCC,bcc);
  }
}
