/** 
 * ????????dataId????ConsumerConfig?listener
 * @param dataId         ??Id
 * @param consumerConfig ???????
 * @param listener       ???????
 */
void addProviderInfoListener(String dataId,ConsumerConfig consumerConfig,ProviderInfoListener listener){
  providerInfoListeners.put(consumerConfig,listener);
  if (LOGGER.isWarnEnabled(consumerConfig.getAppName()) && providerInfoListeners.size() > 5) {
    LOGGER.warnWithApp(consumerConfig.getAppName(),"Duplicate to add provider listener of {} " + "more than 5 times, now is {}, please check it",dataId,providerInfoListeners.size());
  }
}
