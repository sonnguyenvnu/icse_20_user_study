private void registerDiamondListener(final String dataId){
  ConfigChangeListener listener=listenerMap.computeIfAbsent(dataId,i -> new ConfigChangeListener(){
    @Override public void receiveConfigInfo(    String configInfo){
      String md5="";
      if (!StringUtils.isEmpty(configInfo)) {
        try {
          MessageDigest md=MessageDigest.getInstance("MD5");
          md5=new BigInteger(1,md.digest(configInfo.getBytes("UTF-8"))).toString(16);
        }
 catch (        NoSuchAlgorithmException|UnsupportedEncodingException e) {
          log.warn("unable to get md5 for dataId: " + dataId,e);
        }
      }
      refreshHistory.add(dataId,md5);
      applicationContext.publishEvent(new RefreshEvent(this,md5,"ACM Refresh, dataId=" + dataId));
    }
  }
);
  ConfigService.addListener(dataId,acmIntegrationProperties.getAcmProperties().getGroup(),listener);
}
