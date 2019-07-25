public void init() throws Exception {
  super.init();
  if (auth_token == null)   throw new IllegalStateException("no authentication mechanism configured");
  if (auth_token instanceof X509Token) {
    X509Token tmp=(X509Token)auth_token;
    tmp.setCertificate();
  }
  auth_token.init();
}
