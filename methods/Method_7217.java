public static ProxyInfo addProxy(ProxyInfo proxyInfo){
  loadProxyList();
  int count=proxyList.size();
  for (int a=0; a < count; a++) {
    ProxyInfo info=proxyList.get(a);
    if (proxyInfo.address.equals(info.address) && proxyInfo.port == info.port && proxyInfo.username.equals(info.username) && proxyInfo.password.equals(info.password) && proxyInfo.secret.equals(info.secret)) {
      return info;
    }
  }
  proxyList.add(proxyInfo);
  saveProxyList();
  return proxyInfo;
}
