/** 
 * Creates EML string from given  {@link ReceivedEmail}.
 * @param receivedEmail {@link ReceivedEmail} from which to create EML {@link String}.
 * @return {@link String} with EML content.
 */
public String compose(final ReceivedEmail receivedEmail){
  Message msg=receivedEmail.originalMessage();
  final ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
  try {
    msg.writeTo(outputStream);
  }
 catch (  IOException|MessagingException e) {
    throw new MailException(e);
  }
  return outputStream.toString();
}
