/** 
 * ?????????,????<br> 1.??????????????????<br> 2.????????+?????????????
 */
public void notifyStateChangeToUnavailable(){
  final List<ConsumerStateListener> onAvailable=consumerConfig.getOnAvailable();
  if (onAvailable != null) {
    AsyncRuntime.getAsyncThreadPool().execute(new Runnable(){
      @Override public void run(){
        for (        ConsumerStateListener listener : onAvailable) {
          try {
            listener.onUnavailable(consumerConfig.getConsumerBootstrap().getProxyIns());
          }
 catch (          Exception e) {
            LOGGER.errorWithApp(consumerConfig.getAppName(),"Failed to notify consumer state listener when state change to unavailable");
          }
        }
      }
    }
);
  }
}
