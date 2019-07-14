private Map<String,String> parseToken(final String token){
  final String[] parts=token.split(":");
  return ImmutableMap.of(PROPERTY_USERNAME,parts[0],"time",parts[1],"hmac",parts[2]);
}
