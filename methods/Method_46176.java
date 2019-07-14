/** 
 * ?????????Data
 * @param config         ????
 * @param overridePath   ??Path
 * @param data           ???Data
 * @param registerConfig ????
 * @throws Exception ??????
 */
public void removeConfig(AbstractInterfaceConfig config,String overridePath,ChildData data,AbstractInterfaceConfig registerConfig) throws Exception {
  if (data == null) {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      LOGGER.infoWithApp(config.getAppName(),"Receive data is null");
    }
  }
 else   if (registerConfig == null) {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      LOGGER.infoWithApp(config.getAppName(),"Register config is null");
    }
  }
 else {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      LOGGER.infoWithApp(config.getAppName(),"Receive data: path=[" + data.getPath() + "]" + ", data=[" + StringSerializer.decode(data.getData()) + "]" + ", stat=[" + data.getStat() + "]");
    }
    notifyListeners(config,overridePath,data,true,registerConfig);
  }
}
