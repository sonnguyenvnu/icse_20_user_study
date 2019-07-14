private void checkProxyList(){
  for (int a=0, count=SharedConfig.proxyList.size(); a < count; a++) {
    final SharedConfig.ProxyInfo proxyInfo=SharedConfig.proxyList.get(a);
    if (proxyInfo.checking || SystemClock.elapsedRealtime() - proxyInfo.availableCheckTime < 2 * 60 * 1000) {
      continue;
    }
    proxyInfo.checking=true;
    proxyInfo.proxyCheckPingId=ConnectionsManager.getInstance(currentAccount).checkProxy(proxyInfo.address,proxyInfo.port,proxyInfo.username,proxyInfo.password,proxyInfo.secret,time -> AndroidUtilities.runOnUIThread(() -> {
      proxyInfo.availableCheckTime=SystemClock.elapsedRealtime();
      proxyInfo.checking=false;
      if (time == -1) {
        proxyInfo.available=false;
        proxyInfo.ping=0;
      }
 else {
        proxyInfo.ping=time;
        proxyInfo.available=true;
      }
      NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.proxyCheckDone,proxyInfo);
    }
));
  }
}
