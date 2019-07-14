/** 
 * Collect the RPC client information.
 * @param consumerConfig client information model
 */
public void collectConsumerSubInfo(final ConsumerConfig consumerConfig){
  try {
    Id consumerConfigId=rpcLookoutId.fetchConsumerSubId();
    Lookout.registry().info(consumerConfigId,new Info<ConsumerConfig>(){
      @Override public ConsumerConfig value(){
        return consumerConfig;
      }
    }
);
  }
 catch (  Throwable t) {
    LOGGER.error(LogCodes.getLog(LogCodes.ERROR_METRIC_REPORT_ERROR),t);
  }
}
