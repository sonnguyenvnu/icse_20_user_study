/** 
 * Register additional listener.
 * @param itemWriteListener list of {@link ItemWriteListener}s to be registered.
 */
public void register(ItemWriteListener<? super S> itemWriteListener){
  listeners.add(itemWriteListener);
}
