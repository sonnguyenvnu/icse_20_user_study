protected void update(){
  List<ProxyModel> proxies=proxyService.getMonitorActiveProxies();
  addActiveProxies(proxies);
  removeUnusedProxies(proxies);
}
