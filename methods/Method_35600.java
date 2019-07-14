public static void configureFor(String scheme,String host,int port,String proxyHost,int proxyPort){
  defaultInstance.set(WireMock.create().scheme(scheme).host(host).port(port).urlPathPrefix("").hostHeader(null).proxyHost(proxyHost).proxyPort(proxyPort).build());
}
