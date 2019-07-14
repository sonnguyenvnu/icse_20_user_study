/** 
 * Notifies all the added  {@link AnimationBindingListener}s that will start. 
 */
final void notifyWillStart(){
  for (int index=mListeners.size() - 1; index >= 0; index--) {
    final AnimationBindingListener listener=mListeners.get(index);
    listener.onWillStart(this);
  }
}
