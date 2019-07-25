public synchronized void init(String userId,String token,String hosts,int appStatus){
  if (!isActive()) {
    Vector<String> serverUrlList=convertHosts(hosts);
    if (serverUrlList == null || serverUrlList.size() == 0) {
      System.out.println("init IMLibClientBootstrap error,ims hosts is null");
      return;
    }
    isActive=true;
    System.out.println("init IMLibClientBootstrap, servers=" + hosts);
    if (null != imsClient) {
      imsClient.close();
    }
    imsClient=IMSClientFactory.getIMSClient();
    updateAppStatus(appStatus);
    imsClient.init(serverUrlList,new IMSEventListener(userId,token),new IMSConnectStatusListener());
  }
}
