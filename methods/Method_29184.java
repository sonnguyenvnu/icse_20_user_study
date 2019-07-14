/** 
 * ??????(??????????????????????????)
 * @param appDesc
 * @return
 */
private int getClientConnThreshold(AppDesc appDesc){
  int userClientConnThreshold=appDesc.getClientConnAlertValue();
  int systemClientConnThreshold=ConstUtils.APP_CLIENT_CONN_THRESHOLD;
  return userClientConnThreshold > systemClientConnThreshold ? systemClientConnThreshold : userClientConnThreshold;
}
