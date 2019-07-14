/** 
 * Remove provider listener.
 * @param consumerConfig the consumer config
 */
public void removeProviderListener(ConsumerConfig consumerConfig){
  providerListenerMap.remove(consumerConfig);
}
