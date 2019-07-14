/** 
 * Receives all emails and mark all messages as 'seen' (ie 'read').
 * @return array of {@link ReceivedEmail}s.
 * @see #receiveEmailAndMarkSeen(EmailFilter)
 */
public ReceivedEmail[] receiveEmailAndMarkSeen(){
  return receiveEmailAndMarkSeen(null);
}
