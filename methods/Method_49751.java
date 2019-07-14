public DhcpInfo makeDhcpInfo(){
  DhcpInfo info=new DhcpInfo();
  info.ipAddress=convertToInt(ipAddress);
  for (  RouteInfo route : mRoutes) {
    if (route.isDefaultRoute()) {
      info.gateway=convertToInt(route.getGateway().getHostAddress());
      break;
    }
  }
  try {
    info.netmask=NetworkUtilsHelper.prefixLengthToNetmaskInt(prefixLength);
  }
 catch (  IllegalArgumentException e) {
  }
  info.dns1=convertToInt(dns1);
  info.dns2=convertToInt(dns2);
  info.serverAddress=convertToInt(serverAddress);
  info.leaseDuration=leaseDuration;
  return info;
}
