/** 
 * Cancels a pending release for this object.
 * @param releasable Object to cancel release of.
 */
public void cancelDeferredRelease(Releasable releasable){
  ensureOnUiThread();
  mPendingReleasables.remove(releasable);
}
