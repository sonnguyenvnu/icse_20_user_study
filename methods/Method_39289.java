/** 
 * Receives all emails that matches given  {@link EmailFilter} andmark all messages as 'seen' and 'deleted'.
 * @param filter {@link EmailFilter}
 * @return array of {@link ReceivedEmail}s.
 */
public ReceivedEmail[] receiveEmailAndDelete(final EmailFilter filter){
  final Flags flags=new Flags();
  flags.add(Flags.Flag.SEEN);
  flags.add(Flags.Flag.DELETED);
  return receiveMessages(filter,flags,null,false,null);
}
