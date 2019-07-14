public String asAuthorizationHeaderValue(){
  byte[] usernameAndPassword=(username + ":" + password).getBytes();
  return "Basic " + encodeBase64(usernameAndPassword);
}
