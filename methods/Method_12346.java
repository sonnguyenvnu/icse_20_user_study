/** 
 * Associates given  {@link Callback}. If callback has been already added, nothing happens.
 * @param callback Callback to be associated
 */
public void addView(final Callback callback){
  for (int i=0; i < mCallbacks.size(); i++) {
    final CallbackWeakReference reference=mCallbacks.get(i);
    final Callback item=reference.get();
    if (item == null) {
      mCallbacks.remove(reference);
    }
  }
  mCallbacks.addIfAbsent(new CallbackWeakReference(callback));
}
