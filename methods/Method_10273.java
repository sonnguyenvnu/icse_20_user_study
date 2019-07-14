/** 
 * Sets bearer authentication for the request. You should pass in your AuthScope for security. It should be like this setBearerAuth("token", new AuthScope("host",port,AuthScope.ANY_REALM), false)
 * @param token      Bearer Token
 * @param scope      an AuthScope object
 * @param preemptive sets authorization in preemptive manner
 */
public void setBearerAuth(String token,AuthScope scope,boolean preemptive){
  TokenCredentials credentials=new TokenCredentials(token);
  setCredentials(scope,credentials);
  setAuthenticationPreemptive(preemptive);
}
