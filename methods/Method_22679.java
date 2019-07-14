static void handleProxy(String protocol,String hostProp,String portProp){
  String proxyHost=get("proxy." + protocol + ".host");
  String proxyPort=get("proxy." + protocol + ".port");
  if (proxyHost != null && proxyHost.length() != 0 && proxyPort != null && proxyPort.length() != 0) {
    System.setProperty(hostProp,proxyHost);
    System.setProperty(portProp,proxyPort);
  }
}
