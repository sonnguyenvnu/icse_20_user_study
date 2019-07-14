/** 
 * BASIC authentication<br/> Official Example: https://hc.apache.org/httpcomponents-client-ga/httpclient/examples/org /apache/http/examples/client/ClientAuthentication.java
 */
private void addBasicCredentials(BasicAuthInfo authInfo,Map<AuthScope,Credentials> credentialsMap){
  logger.info("BASIC authentication for: {}",authInfo.getLoginTarget());
  Credentials credentials=new UsernamePasswordCredentials(authInfo.getUsername(),authInfo.getPassword());
  credentialsMap.put(new AuthScope(authInfo.getHost(),authInfo.getPort()),credentials);
}
