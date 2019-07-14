/** 
 * Creates SOCKS5 proxy.
 */
public static ProxyInfo socks5Proxy(final String proxyAddress,final int proxyPort,final String proxyUser,final String proxyPassword){
  return new ProxyInfo(ProxyType.SOCKS5,proxyAddress,proxyPort,proxyUser,proxyPassword);
}
