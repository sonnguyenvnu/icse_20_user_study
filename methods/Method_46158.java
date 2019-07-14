/** 
 * ??????Listener
 * @param dataId   ?????
 * @param config   ????????
 * @param listener ???????
 */
void handleDataToListener(String dataId,ConsumerConfig config,ProviderInfoListener listener){
  if (!canNotify()) {
    return;
  }
  if (lastUserData != null) {
    ComposeUserData composeUserData=composeUserAndConfigData(lastUserData,lastConfigData);
    notifyToListener(listener,composeUserData);
  }
}
