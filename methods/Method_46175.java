/** 
 * ?????????Data
 * @param config       ????
 * @param overridePath ??Path
 * @param data         ???Data
 * @throws Exception ??????
 */
public void updateConfig(AbstractInterfaceConfig config,String overridePath,ChildData data) throws Exception {
  if (data == null) {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      LOGGER.infoWithApp(config.getAppName(),"Receive update data is null");
    }
  }
 else {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      LOGGER.infoWithApp(config.getAppName(),"Receive update data: path=[" + data.getPath() + "]" + ", data=[" + StringSerializer.decode(data.getData()) + "]" + ", stat=[" + data.getStat() + "]");
    }
    notifyListeners(config,overridePath,data,false,null);
  }
}
