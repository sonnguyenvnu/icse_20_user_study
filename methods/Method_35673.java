public WireMockBuilder basicAuthenticator(String username,String password){
  return authenticator(new ClientBasicAuthenticator(username,password));
}
