@Override protected SocketFactory resolveSocketFactory(final ProxyInfo proxy,final boolean ssl,final boolean trustAllCertificates,final int connectionTimeout){
  return socketFactory;
}
