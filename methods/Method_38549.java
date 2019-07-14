/** 
 * Creates SOCKS4 proxy.
 */
public static ProxyInfo socks4Proxy(final String proxyAddress,final int proxyPort,final String proxyUser){
  return new ProxyInfo(ProxyType.SOCKS4,proxyAddress,proxyPort,proxyUser,null);
}
