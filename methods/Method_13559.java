private void registerNacosListener(final String group,final String dataId){
  Listener listener=listenerMap.computeIfAbsent(dataId,i -> new Listener(){
    @Override public void receiveConfigInfo(    String configInfo){
      refreshCountIncrement();
      String md5="";
      if (!StringUtils.isEmpty(configInfo)) {
        try {
          MessageDigest md=MessageDigest.getInstance("MD5");
          md5=new BigInteger(1,md.digest(configInfo.getBytes("UTF-8"))).toString(16);
        }
 catch (        NoSuchAlgorithmException|UnsupportedEncodingException e) {
          log.warn("[Nacos] unable to get md5 for dataId: " + dataId,e);
        }
      }
      refreshHistory.add(dataId,md5);
      applicationContext.publishEvent(new RefreshEvent(this,null,"Refresh Nacos config"));
      if (log.isDebugEnabled()) {
        log.debug("Refresh Nacos config group " + group + ",dataId" + dataId);
      }
    }
    @Override public Executor getExecutor(){
      return null;
    }
  }
);
  try {
    configService.addListener(dataId,group,listener);
  }
 catch (  NacosException e) {
    e.printStackTrace();
  }
}
