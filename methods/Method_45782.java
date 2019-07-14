/** 
 * ?????????,????<br> 1.??????????????????<br> 2.????????+?????????????
 */
public void notifyStateChangeToUnavailable(){
  final List<ConsumerStateListener> onprepear=consumerConfig.getOnAvailable();
  if (onprepear != null) {
    AsyncRuntime.getAsyncThreadPool().execute(new Runnable(){
      @Override public void run(){
        for (        ConsumerStateListener listener : onprepear) {
          try {
            listener.onUnavailable(consumerBootstrap.getProxyIns());
          }
 catch (          Exception e) {
            LOGGER.error("Failed to notify consumer state listener when state change to unavailable");
          }
        }
      }
    }
);
  }
}
