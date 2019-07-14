/** 
 * Sets basic authentication for the request. You should pass in your AuthScope for security. It should be like this setBasicAuth("username","password", new AuthScope("host",port,AuthScope.ANY_REALM))
 * @param username   Basic Auth username
 * @param password   Basic Auth password
 * @param scope      an AuthScope object
 * @param preemptive sets authorization in preemptive manner
 */
public void setBasicAuth(String username,String password,AuthScope scope,boolean preemptive){
  UsernamePasswordCredentials credentials=new UsernamePasswordCredentials(username,password);
  setCredentials(scope,credentials);
  setAuthenticationPreemptive(preemptive);
}
