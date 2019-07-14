/** 
 * Receives all emails that matches given  {@link EmailFilter}and mark them as 'seen' (ie 'read').
 * @param filter {@link EmailFilter}
 * @return array of {@link ReceivedEmail}s.
 */
public ReceivedEmail[] receiveEmailAndMarkSeen(final EmailFilter filter){
  final Flags flagsToSet=new Flags();
  flagsToSet.add(Flags.Flag.SEEN);
  return receiveMessages(filter,flagsToSet,null,false,null);
}
