/** 
 * ????????,????<br> 1.?????????<br> 2.?????????????????<br> 3.?????????????????????
 */
public void notifyStateChangeToAvailable(){
  final List<ConsumerStateListener> onAvailable=consumerConfig.getOnAvailable();
  if (onAvailable != null) {
    AsyncRuntime.getAsyncThreadPool().execute(new Runnable(){
      @Override public void run(){
        for (        ConsumerStateListener listener : onAvailable) {
          try {
            listener.onAvailable(consumerConfig.getConsumerBootstrap().getProxyIns());
          }
 catch (          Exception e) {
            LOGGER.warnWithApp(consumerConfig.getAppName(),"Failed to notify consumer state listener when state change to available");
          }
        }
      }
    }
);
  }
}
