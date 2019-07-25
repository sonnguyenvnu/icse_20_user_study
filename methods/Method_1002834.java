private static String hostname(RequestHeaders headers){
  final String authority=headers.authority();
  assert authority != null;
  final int hostnameColonIdx=authority.lastIndexOf(':');
  if (hostnameColonIdx < 0) {
    return authority;
  }
  return authority.substring(0,hostnameColonIdx);
}
