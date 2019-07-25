public static boolean match(InetAddress addr,AddressScope scope){
  if (scope == null)   return true;
switch (scope) {
case GLOBAL:
    return !addr.isLoopbackAddress() && !addr.isLinkLocalAddress() && !addr.isSiteLocalAddress();
case SITE_LOCAL:
  return addr.isSiteLocalAddress();
case LINK_LOCAL:
return addr.isLinkLocalAddress();
case LOOPBACK:
return addr.isLoopbackAddress();
case NON_LOOPBACK:
return !addr.isLoopbackAddress();
default :
throw new IllegalArgumentException("scope " + scope + " is unknown");
}
}
