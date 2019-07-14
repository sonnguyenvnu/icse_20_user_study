/** 
 * @return Whether this token is associated with the given session.
 */
public boolean isAssociatedWith(CustomTabsSession session){
  return session.getBinder().equals(mCallbackBinder);
}
