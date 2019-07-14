/** 
 * Do NT auth for Microsoft AD sites.
 */
private void addNtCredentials(NtAuthInfo authInfo,Map<AuthScope,Credentials> credentialsMap){
  logger.info("NT authentication for: {}",authInfo.getLoginTarget());
  try {
    Credentials credentials=new NTCredentials(authInfo.getUsername(),authInfo.getPassword(),InetAddress.getLocalHost().getHostName(),authInfo.getDomain());
    credentialsMap.put(new AuthScope(authInfo.getHost(),authInfo.getPort()),credentials);
  }
 catch (  UnknownHostException e) {
    logger.error("Error creating NT credentials",e);
  }
}
