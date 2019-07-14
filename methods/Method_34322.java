/** 
 * Convenience method to check if there is a user associated with this token, or just a client application.
 * @return true if this token represents a client app not acting on behalf of a user
 */
public boolean isClientOnly(){
  return userAuthentication == null;
}
