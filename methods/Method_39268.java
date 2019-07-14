/** 
 * Returns email store.
 * @param session {@link Session}
 * @return {@link com.sun.mail.pop3.POP3SSLStore}
 */
@Override protected POP3SSLStore getStore(final Session session){
  final SimpleAuthenticator simpleAuthenticator=(SimpleAuthenticator)authenticator;
  final URLName url;
  if (simpleAuthenticator == null) {
    url=new URLName(PROTOCOL_POP3,host,port,StringPool.EMPTY,null,null);
  }
 else {
    final PasswordAuthentication pa=simpleAuthenticator.getPasswordAuthentication();
    url=new URLName(PROTOCOL_POP3,host,port,StringPool.EMPTY,pa.getUserName(),pa.getPassword());
  }
  return new POP3SSLStore(session,url);
}
