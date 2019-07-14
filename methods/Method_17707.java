/** 
 * Checks with all added  {@link AnimationBindingListener} if should start
 * @return true if *all* listeners return true from {@link AnimationBindingListener#shouldStart(AnimationBinding)}, false - otherwise
 */
final boolean shouldStart(){
  for (int index=mListeners.size() - 1; index >= 0; index--) {
    final AnimationBindingListener listener=mListeners.get(index);
    if (!listener.shouldStart(this)) {
      return false;
    }
  }
  return true;
}
