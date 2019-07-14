@Override public ProxySettings proxyVia(){
  if (optionSet.has(PROXY_VIA)) {
    String proxyVia=(String)optionSet.valueOf(PROXY_VIA);
    return ProxySettings.fromString(proxyVia);
  }
  return NO_PROXY;
}
