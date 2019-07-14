/** 
 * ?????????Data
 * @param config     ????
 * @param configPath ??Path
 * @param data       ???Data
 */
public void removeConfig(AbstractInterfaceConfig config,String configPath,ChildData data){
  if (data == null) {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      LOGGER.infoWithApp(config.getAppName(),"Receive remove data is null");
    }
  }
 else {
    if (LOGGER.isInfoEnabled(config.getAppName())) {
      LOGGER.infoWithApp(config.getAppName(),"Receive remove data: path=[" + data.getPath() + "]" + ", data=[" + StringSerializer.decode(data.getData()) + "]" + ", stat=[" + data.getStat() + "]");
    }
    notifyListeners(config,configPath,data,true);
  }
}
