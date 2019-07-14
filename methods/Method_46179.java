/** 
 * Add provider listener.
 * @param consumerConfig the consumer config
 * @param listener       the listener
 */
public void addProviderListener(ConsumerConfig consumerConfig,ProviderInfoListener listener){
  if (listener != null) {
    RegistryUtils.initOrAddList(providerListenerMap,consumerConfig,listener);
  }
}
