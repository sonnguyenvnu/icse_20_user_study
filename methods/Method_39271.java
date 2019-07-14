/** 
 * Parses  {@link Message} and extracts all data for the received message.
 * @param msg {@link Message} to parse.
 * @throws IOException        if there is an error with the content
 * @throws MessagingException if there is an error.
 */
protected void parseMessage(final Message msg,final boolean envelope) throws MessagingException, IOException {
  flags(msg.getFlags());
  messageNumber(msg.getMessageNumber());
  if (msg instanceof MimeMessage) {
    messageId(((MimeMessage)msg).getMessageID());
  }
  final Address[] addresses=msg.getFrom();
  if (addresses != null && addresses.length > 0) {
    from(addresses[0]);
  }
  replyTo(msg.getReplyTo());
  to(msg.getRecipients(Message.RecipientType.TO));
  cc(msg.getRecipients(Message.RecipientType.CC));
  subject(msg.getSubject());
  receivedDate(msg.getReceivedDate());
  sentDate(msg.getSentDate());
  headers(msg.getAllHeaders());
  if (!envelope) {
    processPart(msg);
  }
}
