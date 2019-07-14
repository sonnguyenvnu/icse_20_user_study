/** 
 * Register a callback to be invoked when the value changes.
 * @param listener The callback to invoke.
 */
void attachListener(OnValueChangeListener<T> listener){
  mListeners.add(listener);
}
