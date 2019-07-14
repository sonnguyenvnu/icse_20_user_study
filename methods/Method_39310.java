/** 
 * Sets FROM, REPLY-TO and recipients.
 * @param emailWithData {@link Email} with data
 * @param msgToSet      {@link MimeMessage} to set data into.
 * @throws MessagingException if there is a failure
 */
private void setPeople(final Email emailWithData,final MimeMessage msgToSet) throws MessagingException {
  msgToSet.setFrom(emailWithData.from().toInternetAddress());
  msgToSet.setReplyTo(EmailAddress.convert(emailWithData.replyTo()));
  setRecipients(emailWithData,msgToSet);
}
