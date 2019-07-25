/** 
 * Register additional listener.
 * @param itemProcessorListener instance  of {@link ItemProcessListener} to be registered.
 */
public void register(ItemProcessListener<? super T,? super S> itemProcessorListener){
  listeners.add(itemProcessorListener);
}
