/** 
 * Creates EML string from given  {@link Email}.
 * @param email {@link Email} from which to create EML {@link String}.
 * @return {@link String} with EML content.
 */
public String compose(final Email email){
  if (getSession() == null) {
    createSession(getProperties());
  }
  final OutputStreamTransport ost=new OutputStreamTransport(getSession());
  final SendMailSession sendMailSession=new SendMailSession(getSession(),ost);
  sendMailSession.sendMail(email);
  return ost.getEml();
}
