protected String encodeAuthorization(String auth){
  return "basic ".concat(Base64.encodeBase64String(auth.getBytes()));
}
