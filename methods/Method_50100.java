private static String decodePageToken(String encodedToken){
  return new String(Base64.getDecoder().decode(encodedToken.getBytes()));
}
