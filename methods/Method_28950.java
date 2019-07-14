/** 
 * @param hostName
 * @return ip address or hostName if UnknownHostException
 */
public static String getIpByHost(String hostName){
  try {
    String ip=hostIpCache.get(hostName);
    if (ip == "" || "".equals(hostName.trim())) {
      ip=InetAddress.getByName(hostName).getHostAddress();
      hostIpCache.putIfAbsent(hostName,ip);
    }
    return ip;
  }
 catch (  UnknownHostException e) {
    return hostName;
  }
}
