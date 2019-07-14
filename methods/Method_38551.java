/** 
 * Creates HTTP proxy.
 */
public static ProxyInfo httpProxy(final String proxyAddress,final int proxyPort,final String proxyUser,final String proxyPassword){
  return new ProxyInfo(ProxyType.HTTP,proxyAddress,proxyPort,proxyUser,proxyPassword);
}
