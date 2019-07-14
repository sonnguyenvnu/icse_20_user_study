private String encodeBasicAuthenticationString(){
  if (userId != null && password != null) {
    return "Basic " + BASE64Encoder.encode((userId + ":" + password).getBytes());
  }
  return null;
}
