/** 
 * Gets the online member count.
 * @return online member count
 */
public int getOnlineMemberCount(){
  int ret=0;
  for (  final Set<Session> value : UserChannel.SESSIONS.values()) {
    ret+=value.size();
  }
  return ret;
}
