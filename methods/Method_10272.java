/** 
 * Sets bearer authentication for the request. Uses AuthScope.ANY. This is the same as setBearerAuth('token',AuthScope.ANY, false)
 * @param token Bearer Token
 */
public void setBearerAuth(String token){
  setBearerAuth(token,AuthScope.ANY,false);
}
