/** 
 * Implementation method for authentication
 */
private String authenticate(Credentials credentials) throws AuthenticationException {
  if (!(credentials instanceof EsApiKeyCredentials)) {
    throw new AuthenticationException("Incorrect credentials type provided. Expected [" + EsApiKeyCredentials.class.getName() + "] but got [" + credentials.getClass().getName() + "]");
  }
  EsApiKeyCredentials esApiKeyCredentials=((EsApiKeyCredentials)credentials);
  String authString=null;
  if (esApiKeyCredentials.getToken() != null && StringUtils.hasText(esApiKeyCredentials.getToken().getName())) {
    EsToken token=esApiKeyCredentials.getToken();
    String keyComponents=token.getId() + ":" + token.getApiKey();
    byte[] base64Encoded=Base64.encodeBase64(keyComponents.getBytes(StringUtils.UTF_8));
    String tokenText=new String(base64Encoded,StringUtils.UTF_8);
    authString=EsHadoopAuthPolicies.APIKEY + " " + tokenText;
  }
  return authString;
}
