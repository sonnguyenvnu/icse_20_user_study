/** 
 * Register additional listener.
 * @param itemReaderListener instance of {@link ItemReadListener} to be registered.
 */
public void register(ItemReadListener<? super T> itemReaderListener){
  listeners.add(itemReaderListener);
}
