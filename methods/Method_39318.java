/** 
 * {@inheritDoc}
 * @return {@link SendMailSession}
 */
@Override public SendMailSession createSession(){
  final Session session=Session.getInstance(createSessionProperties(),authenticator);
  final Transport mailTransport;
  try {
    mailTransport=getTransport(session);
  }
 catch (  final NoSuchProviderException nspex) {
    throw new MailException(nspex);
  }
  return new SendMailSession(session,mailTransport);
}
