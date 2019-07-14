/** 
 * Removes the provided listener from child attached state listeners list.
 * @param listener Listener to unregister
 */
public void removeOnChildAttachStateChangeListener(@NonNull OnChildAttachStateChangeListener listener){
  if (mOnChildAttachStateListeners == null) {
    return;
  }
  mOnChildAttachStateListeners.remove(listener);
}
