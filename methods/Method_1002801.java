@Nullable @Override public OAuth1aToken apply(RequestHeaders headers){
  final String authorization=requireNonNull(headers,"headers").get(header);
  if (Strings.isNullOrEmpty(authorization)) {
    return null;
  }
  final Matcher matcher=AUTHORIZATION_HEADER_PATTERN.matcher(authorization);
  if (!matcher.matches()) {
    logger.warn("Invalid authorization header: " + authorization);
    return null;
  }
  final ImmutableMap.Builder<String,String> builder=ImmutableMap.builder();
  for (  String token : matcher.group("parameters").split(",")) {
    final int sep=token.indexOf('=');
    if (sep == -1 || token.charAt(sep + 1) != '"' || token.charAt(token.length() - 1) != '"') {
      logger.warn("Invalid token: " + token);
      return null;
    }
    final String key=token.substring(0,sep);
    final String value=token.substring(sep + 2,token.length() - 1);
    builder.put(key,value);
  }
  return OAuth1aToken.of(builder.build());
}
