/** 
 * Notifies all the added  {@link AnimationBindingListener}s that has finished. 
 */
final void notifyFinished(){
  for (int index=mListeners.size() - 1; index >= 0; index--) {
    final AnimationBindingListener listener=mListeners.get(index);
    listener.onFinish(this);
  }
}
