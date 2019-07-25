/** 
 * @param items the items to send
 * @see ItemWriter#write(List)
 */
@Override public void write(List<? extends MimeMessage> items) throws MailException {
  try {
    mailSender.send(items.toArray(new MimeMessage[items.size()]));
  }
 catch (  MailSendException e) {
    Map<Object,Exception> failedMessages=e.getFailedMessages();
    for (    Entry<Object,Exception> entry : failedMessages.entrySet()) {
      mailErrorHandler.handle(new MimeMailMessage((MimeMessage)entry.getKey()),entry.getValue());
    }
  }
}
