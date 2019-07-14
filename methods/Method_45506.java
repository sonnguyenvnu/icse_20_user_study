/** 
 * ???ip?????????????????IP??
 * @param registryIp ??????
 * @return ???ip???????????????IP??
 */
public static String getLocalHostByRegistry(String registryIp){
  String host=null;
  if (registryIp != null && registryIp.length() > 0) {
    List<InetSocketAddress> addrs=getIpListByRegistry(registryIp);
    for (int i=0; i < addrs.size(); i++) {
      InetAddress address=getLocalHostBySocket(addrs.get(i));
      if (address != null) {
        host=address.getHostAddress();
        if (host != null && !NetUtils.isInvalidLocalHost(host)) {
          return host;
        }
      }
    }
  }
  if (NetUtils.isInvalidLocalHost(host)) {
    host=NetUtils.getLocalIpv4();
  }
  return host;
}
