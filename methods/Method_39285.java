/** 
 * Receives all emails. Messages are not modified. However, servers may set SEEN flag anyway, so we force messages to remain unseen.
 * @return array of {@link ReceivedEmail}s.
 */
public ReceivedEmail[] receiveEmail(){
  return receiveMessages(null,null,null,false,null);
}
