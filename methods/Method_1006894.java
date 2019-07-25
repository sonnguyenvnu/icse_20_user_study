/** 
 * Register additional listener.
 * @param listener instance of {@link SkipListener} to be registered.
 */
public void register(SkipListener<? super T,? super S> listener){
  listeners.add(listener);
}
