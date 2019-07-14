private Map<String,String> getAuthCredMap(final String authorizationHeader,final String authType){
  final byte[] decodedAuthParams;
  final String authorization;
  try {
    final String encodedAuthParams=authorizationHeader.substring(authType.length());
    decodedAuthParams=decoder.decode(encodedAuthParams);
    authorization=new String(decodedAuthParams);
  }
 catch (  IndexOutOfBoundsException|IllegalArgumentException e) {
    return null;
  }
  final Map<String,String> credentials=new HashMap<>();
  if (authType.equals(basic)) {
    final String[] split=authorization.split(":");
    if (split.length != 2) {
      return null;
    }
    credentials.put(PROPERTY_USERNAME,split[0]);
    credentials.put(PROPERTY_PASSWORD,split[1]);
  }
 else {
    credentials.put(PROPERTY_TOKEN,authorization);
  }
  return credentials;
}
