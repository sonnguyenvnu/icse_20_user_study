/** 
 * Defines proxy for a browser.
 */
public HttpBrowser setProxyInfo(final ProxyInfo proxyInfo){
  httpConnectionProvider.useProxy(proxyInfo);
  return this;
}
