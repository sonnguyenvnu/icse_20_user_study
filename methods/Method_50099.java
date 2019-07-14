private static String encodePageToken(String token){
  return new String(Base64.getEncoder().encode(token.getBytes()));
}
