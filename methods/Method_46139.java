/** 
 * Remove provider listener.
 * @param consumerConfig the consumer config
 */
void removeProviderListener(ConsumerConfig consumerConfig){
  providerListenerMap.remove(consumerConfig);
}
