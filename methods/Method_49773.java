public static RouteInfo makeHostRoute(InetAddress host,InetAddress gateway){
  if (host == null)   return null;
  if (host instanceof Inet4Address) {
    return new RouteInfo(new LinkAddress(host,32),gateway);
  }
 else {
    return new RouteInfo(new LinkAddress(host,128),gateway);
  }
}
