/** 
 * The name of the config attribute that can be used to deny access to OAuth2 client. Defaults to <code>DENY_OAUTH</code>.
 * @param denyAccess the deny access attribute value to set
 */
public void setDenyAccess(String denyAccess){
  this.clientHasScope=denyAccess;
}
