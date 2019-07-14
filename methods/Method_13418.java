public void exportURL(URL url){
  URL actualURL=url;
  InetUtils.HostInfo hostInfo=inetUtils.findFirstNonLoopbackHostInfo();
  String ipAddress=hostInfo.getIpAddress();
  if (!Objects.equals(url.getHost(),ipAddress)) {
    actualURL=url.setHost(ipAddress);
  }
  this.allExportedURLs.add(actualURL.getServiceKey(),actualURL);
}
