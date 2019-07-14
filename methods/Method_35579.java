private ProxySettings createProxySettings(String proxyHost,int proxyPort){
  if (StringUtils.isNotBlank(proxyHost)) {
    return new ProxySettings(proxyHost,proxyPort);
  }
  return ProxySettings.NO_PROXY;
}
