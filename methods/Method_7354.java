public static String[] getLocalNetworkAddressesAndInterfaceName(){
  ConnectivityManager cm=(ConnectivityManager)ApplicationLoader.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    Network net=cm.getActiveNetwork();
    if (net == null)     return null;
    LinkProperties linkProps=cm.getLinkProperties(net);
    if (linkProps == null)     return null;
    String ipv4=null, ipv6=null;
    for (    LinkAddress addr : linkProps.getLinkAddresses()) {
      InetAddress a=addr.getAddress();
      if (a instanceof Inet4Address) {
        if (!a.isLinkLocalAddress()) {
          ipv4=a.getHostAddress();
        }
      }
 else       if (a instanceof Inet6Address) {
        if (!a.isLinkLocalAddress() && (a.getAddress()[0] & 0xF0) != 0xF0) {
          ipv6=a.getHostAddress();
        }
      }
    }
    return new String[]{linkProps.getInterfaceName(),ipv4,ipv6};
  }
 else {
    try {
      Enumeration<NetworkInterface> itfs=NetworkInterface.getNetworkInterfaces();
      if (itfs == null)       return null;
      while (itfs.hasMoreElements()) {
        NetworkInterface itf=itfs.nextElement();
        if (itf.isLoopback() || !itf.isUp())         continue;
        Enumeration<InetAddress> addrs=itf.getInetAddresses();
        String ipv4=null, ipv6=null;
        while (addrs.hasMoreElements()) {
          InetAddress a=addrs.nextElement();
          if (a instanceof Inet4Address) {
            if (!a.isLinkLocalAddress()) {
              ipv4=a.getHostAddress();
            }
          }
 else           if (a instanceof Inet6Address) {
            if (!a.isLinkLocalAddress() && (a.getAddress()[0] & 0xF0) != 0xF0) {
              ipv6=a.getHostAddress();
            }
          }
        }
        return new String[]{itf.getName(),ipv4,ipv6};
      }
      return null;
    }
 catch (    Exception x) {
      FileLog.e(x);
      return null;
    }
  }
}
