protected String[] decodeClientAuthenticationHeader(String authenticationHeader){
  if (StringUtils.isNullOrEmpty(authenticationHeader)) {
    return null;
  }
 else {
    String[] tokens=authenticationHeader.split(" ");
    if (tokens.length != 2) {
      return null;
    }
 else {
      String authType=tokens[0];
      if (!"basic".equalsIgnoreCase(authType)) {
        return ErrorType.OTHER.throwThis(GrantTokenException::new,"authentication " + authType + " not support!");
      }
 else {
        String encodedCreds=tokens[1];
        return decodeBase64EncodedCredentials(encodedCreds);
      }
    }
  }
}
