/** 
 * create consumerId
 * @return consumerId
 */
public Id fetchConsumerStatId(){
  if (consumerId == null) {
synchronized (consumerIdLock) {
      if (consumerId == null) {
        consumerId=Lookout.registry().createId("rpc.consumer.service.stats");
      }
    }
  }
  return consumerId;
}
