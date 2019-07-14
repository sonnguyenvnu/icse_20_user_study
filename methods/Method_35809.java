public WireMockConfiguration basicAdminAuthenticator(String username,String password){
  return adminAuthenticator(new BasicAuthenticator(username,password));
}
