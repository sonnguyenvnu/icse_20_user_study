/** 
 * Returns socket factory based on proxy type and SSL requirements.
 */
protected SocketFactory resolveSocketFactory(final ProxyInfo proxy,final boolean ssl,final boolean trustAllCertificates,final int connectionTimeout) throws IOException {
switch (proxy.getProxyType()) {
case NONE:
    if (ssl) {
      return getDefaultSSLSocketFactory(trustAllCertificates);
    }
 else {
      return SocketFactory.getDefault();
    }
case HTTP:
  return new HTTPProxySocketFactory(proxy,connectionTimeout);
case SOCKS4:
return new Socks4ProxySocketFactory(proxy,connectionTimeout);
case SOCKS5:
return new Socks5ProxySocketFactory(proxy,connectionTimeout);
default :
throw new HttpException("Invalid proxy type " + proxy.getProxyType());
}
}
