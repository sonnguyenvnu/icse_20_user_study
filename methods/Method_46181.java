/** 
 * Update Provider
 * @param config       ConsumerConfig
 * @param providerPath Provider path of zookeeper
 * @param data         Event data
 * @param currentData  provider data list
 * @throws UnsupportedEncodingException decode error
 */
public void updateProvider(ConsumerConfig config,String providerPath,ChildData data,List<ChildData> currentData) throws UnsupportedEncodingException {
  if (LOGGER.isInfoEnabled(config.getAppName())) {
    LOGGER.infoWithApp(config.getAppName(),"Receive update provider: path=[" + data.getPath() + "]" + ", data=[" + StringSerializer.decode(data.getData()) + "]" + ", stat=[" + data.getStat() + "]" + ", list=[" + currentData.size() + "]");
  }
  notifyListeners(config,providerPath,currentData,false);
}
