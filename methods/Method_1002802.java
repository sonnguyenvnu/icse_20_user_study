@Nullable @Override public OAuth2Token apply(RequestHeaders headers){
  final String authorization=requireNonNull(headers,"headers").get(header);
  if (Strings.isNullOrEmpty(authorization)) {
    return null;
  }
  final Matcher matcher=AUTHORIZATION_HEADER_PATTERN.matcher(authorization);
  if (!matcher.matches()) {
    logger.warn("Invalid authorization header: " + authorization);
    return null;
  }
  return OAuth2Token.of(matcher.group("accessToken"));
}
