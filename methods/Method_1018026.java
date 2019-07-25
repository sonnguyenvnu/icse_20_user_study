/** 
 * Registers this Spark Shell client with the remote Kylo services server.
 */
public void register(){
  Preconditions.checkState(serverUrl != null,"Registration server is not available.");
  final URL url;
  try {
    url=new URL(serverUrl);
  }
 catch (  MalformedURLException e) {
    throw new IllegalStateException("Not a valid registration URL: " + serverUrl);
  }
  Preconditions.checkNotNull(clientId,"Environment variable is not defined: KYLO_CLIENT_ID");
  Preconditions.checkNotNull(clientSecret,"Environment variable is not defined: KYLO_CLIENT_SECRET");
  final char[] keystorePasswordChars=(keystorePassword != null) ? keystorePassword.toCharArray() : null;
  final JerseyClientConfig config=new JerseyClientConfig(url.getHost(),clientId,clientSecret.toCharArray(),url.getProtocol().equalsIgnoreCase("https"),false,keystorePath,keystorePasswordChars);
  config.setPort(url.getPort() > 0 ? url.getPort() : url.getDefaultPort());
  final JerseyRestClient client=getRestClient(config);
  final String hostName=getHostName();
  log.info("Registering client {} at {}:{} with server {}.",clientId,hostName,localPort,serverUrl);
  final Response response=client.post(url.getPath(),ImmutableMap.of("host",hostName,"port",localPort));
  if (response != null && response.getStatus() >= 200 && response.getStatus() < 300) {
    log.info("Successfully registered client.");
  }
 else {
    log.info("Registration failed with response: {}",response);
    throw new IllegalStateException("Failed to register with server");
  }
}
