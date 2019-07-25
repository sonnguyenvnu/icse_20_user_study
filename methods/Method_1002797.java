@Nullable @Override public BasicToken apply(RequestHeaders headers){
  final String authorization=requireNonNull(headers,"headers").get(header);
  if (Strings.isNullOrEmpty(authorization)) {
    return null;
  }
  final Matcher matcher=AUTHORIZATION_HEADER_PATTERN.matcher(authorization);
  if (!matcher.matches()) {
    logger.warn("Invalid authorization header: {}",authorization);
    return null;
  }
  final String base64=matcher.group("encoded");
  final byte[] decoded;
  try {
    decoded=BASE64_DECODER.decode(base64);
  }
 catch (  IllegalArgumentException e) {
    logger.warn("Base64 decoding failed: {}",base64);
    return null;
  }
  final String credential=new String(decoded,StandardCharsets.UTF_8);
  final int sep=credential.indexOf(':');
  if (sep == -1) {
    logger.warn("Invalid credential: {}",credential);
    return null;
  }
  final String username=credential.substring(0,sep);
  final String password=credential.substring(sep + 1);
  return BasicToken.of(username,password);
}
