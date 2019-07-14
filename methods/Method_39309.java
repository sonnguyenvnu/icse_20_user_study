/** 
 * Prepares message and sends it. Returns Message ID of sent email.
 * @param email {@link Email} to send.
 * @return String representing message ID.
 */
public String sendMail(final Email email){
  try {
    final MimeMessage msg=createMessage(email);
    getService().sendMessage(msg,msg.getAllRecipients());
    return msg.getMessageID();
  }
 catch (  final MessagingException msgexc) {
    throw new MailException("Failed to send email: " + email,msgexc);
  }
}
