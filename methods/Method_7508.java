@SuppressLint("NewApi") protected static boolean useIpv6Address(){
  if (Build.VERSION.SDK_INT < 19) {
    return false;
  }
  if (BuildVars.LOGS_ENABLED) {
    try {
      NetworkInterface networkInterface;
      Enumeration<NetworkInterface> networkInterfaces=NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        networkInterface=networkInterfaces.nextElement();
        if (!networkInterface.isUp() || networkInterface.isLoopback() || networkInterface.getInterfaceAddresses().isEmpty()) {
          continue;
        }
        if (BuildVars.LOGS_ENABLED) {
          FileLog.d("valid interface: " + networkInterface);
        }
        List<InterfaceAddress> interfaceAddresses=networkInterface.getInterfaceAddresses();
        for (int a=0; a < interfaceAddresses.size(); a++) {
          InterfaceAddress address=interfaceAddresses.get(a);
          InetAddress inetAddress=address.getAddress();
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("address: " + inetAddress.getHostAddress());
          }
          if (inetAddress.isLinkLocalAddress() || inetAddress.isLoopbackAddress() || inetAddress.isMulticastAddress()) {
            continue;
          }
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("address is good");
          }
        }
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
  try {
    NetworkInterface networkInterface;
    Enumeration<NetworkInterface> networkInterfaces=NetworkInterface.getNetworkInterfaces();
    boolean hasIpv4=false;
    boolean hasIpv6=false;
    while (networkInterfaces.hasMoreElements()) {
      networkInterface=networkInterfaces.nextElement();
      if (!networkInterface.isUp() || networkInterface.isLoopback()) {
        continue;
      }
      List<InterfaceAddress> interfaceAddresses=networkInterface.getInterfaceAddresses();
      for (int a=0; a < interfaceAddresses.size(); a++) {
        InterfaceAddress address=interfaceAddresses.get(a);
        InetAddress inetAddress=address.getAddress();
        if (inetAddress.isLinkLocalAddress() || inetAddress.isLoopbackAddress() || inetAddress.isMulticastAddress()) {
          continue;
        }
        if (inetAddress instanceof Inet6Address) {
          hasIpv6=true;
        }
 else         if (inetAddress instanceof Inet4Address) {
          String addrr=inetAddress.getHostAddress();
          if (!addrr.startsWith("192.0.0.")) {
            hasIpv4=true;
          }
        }
      }
    }
    if (!hasIpv4 && hasIpv6) {
      return true;
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
  return false;
}
