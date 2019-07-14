protected ClientCredentials getClientCredentials(String principal,String credentials,String authorization){
  if ((principal == null || credentials == null) && authorization == null) {
    return null;
  }
  if (authorization != null && !authorization.isEmpty()) {
    String[] decodeCredentials=decodeClientAuthenticationHeader(authorization);
    if (decodeCredentials == null) {
      return null;
    }
    if (decodeCredentials.length > 1) {
      principal=decodeCredentials[0];
      credentials=decodeCredentials[1];
    }
 else {
      credentials=decodeCredentials[0];
    }
  }
  return new ClientCredentials(principal,credentials);
}
