/** 
 * Disassociates given  {@link Callback}. If callback is not associated, nothing happens.
 * @param callback Callback to be disassociated
 */
public void removeView(final Callback callback){
  for (int i=0; i < mCallbacks.size(); i++) {
    final CallbackWeakReference reference=mCallbacks.get(i);
    final Callback item=reference.get();
    if (item == null || item == callback) {
      mCallbacks.remove(reference);
    }
  }
}
