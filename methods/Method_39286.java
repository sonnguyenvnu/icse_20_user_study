/** 
 * Receives all emails that matches given  {@link EmailFilter}. Messages are not modified. However, servers may set SEEN flag anyway, so we force messages to remain unseen.
 * @param filter {@link EmailFilter}
 * @return array of {@link ReceivedEmail}s.
 */
public ReceivedEmail[] receiveEmail(final EmailFilter filter){
  return receiveMessages(filter,null,null,false,null);
}
