/** 
 * The authorization code for this context.
 * @return The authorization code, or null if none.
 */
public String getAuthorizationCode(){
  return getFirst("code");
}
