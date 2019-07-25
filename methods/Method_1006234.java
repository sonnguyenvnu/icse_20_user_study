public static void register(ProxyPreferences proxyPrefs){
  if (proxyPrefs.isUseProxy()) {
    System.setProperty("http.proxyHost",proxyPrefs.getHostname());
    System.setProperty("http.proxyPort",proxyPrefs.getPort());
    System.setProperty("https.proxyHost",proxyPrefs.getHostname());
    System.setProperty("https.proxyPort",proxyPrefs.getPort());
    if (proxyPrefs.isUseAuthentication()) {
      System.setProperty("http.proxyUser",proxyPrefs.getUsername());
      System.setProperty("http.proxyPassword",proxyPrefs.getPassword());
      System.setProperty("https.proxyUser",proxyPrefs.getUsername());
      System.setProperty("https.proxyPassword",proxyPrefs.getPassword());
    }
  }
 else {
    System.setProperty("java.net.useSystemProxies","true");
    System.setProperty("proxySet","true");
  }
}
