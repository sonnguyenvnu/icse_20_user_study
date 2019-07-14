/** 
 * @param protocol          Protocol such as {@link ImapServer#PROTOCOL_IMAP} or {@link Pop3Server#PROTOCOL_POP3}.
 * @param sessionProperties Session properties to use.
 * @param authenticator     Authenticator which contains necessary authentication for server.
 * @return {@link ReceiveMailSession}.
 */
public static ReceiveMailSession createSession(final String protocol,final Properties sessionProperties,final Authenticator authenticator,final File attachmentStorage){
  final Session session=Session.getInstance(sessionProperties,authenticator);
  final Store store;
  try {
    store=session.getStore(protocol);
  }
 catch (  final NoSuchProviderException nspex) {
    final String errMsg=String.format("Failed to create %s session",protocol);
    throw new MailException(errMsg,nspex);
  }
  return new ReceiveMailSession(session,store,attachmentStorage);
}
