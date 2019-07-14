/** 
 * {@inheritDoc}
 * @return {@link ReceiveMailSession}
 */
@Override public ReceiveMailSession createSession(){
  return EmailUtil.createSession(PROTOCOL_IMAP,createSessionProperties(),authenticator,attachmentStorage);
}
