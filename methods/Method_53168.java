/** 
 * @return authentication URLsince Twitter4J 2.0.10
 */
public String getAuthenticationURL(){
  return conf.getOAuthAuthenticationURL() + "?oauth_token=" + getToken();
}
