private static boolean isValidIntranetAddress(InetAddress address){
  if (address == null || address.isLoopbackAddress())   return false;
  String name=address.getHostAddress();
  return (name != null && !ANYHOST.equals(name) && !LOCALHOST.equals(name) && IP_PATTERN.matcher(name).matches() && IP_INTRANET_PATTERN.matcher(name).matches());
}
