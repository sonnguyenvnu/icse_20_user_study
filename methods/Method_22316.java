/** 
 * <p> Set credentials for this HttpSender that override (if present) the ones set globally. </p>
 * @param username The username to set for HTTP Basic Auth.
 * @param password The password to set for HTTP Basic Auth.
 */
@SuppressWarnings("unused") public void setBasicAuth(@Nullable String username,@Nullable String password){
  mUsername=username;
  mPassword=password;
}
