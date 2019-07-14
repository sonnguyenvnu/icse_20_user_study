/** 
 * Remove a callback.
 * @param listener The listener to detach
 */
void detach(OnValueChangeListener<T> listener){
  mListeners.remove(listener);
}
