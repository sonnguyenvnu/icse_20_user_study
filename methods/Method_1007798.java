/** 
 * Get whether a session exists for the given owner.
 * @param owner the owner
 * @return true if a session exists
 */
public synchronized boolean contains(SessionOwner owner){
  checkNotNull(owner);
  return sessions.containsKey(getKey(owner));
}
