/** 
 * Wraps the input exception with a runtime  {@link MailException}. The exception message will contain the failed message (using toString).
 * @param message a failed message
 * @param exception a MessagingException
 * @throws MailException a translation of the Exception
 * @see MailErrorHandler#handle(MailMessage,Exception)
 */
@Override public void handle(MailMessage message,Exception exception) throws MailException {
  String msg=message.toString();
  throw new MailSendException("Mail server send failed: " + msg.substring(0,Math.min(maxMessageLength,msg.length())),exception);
}
