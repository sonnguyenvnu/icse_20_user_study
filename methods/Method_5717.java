/** 
 * Calls  {@link InvalidationListener#onTrackSelectionsInvalidated()} to invalidate all previouslygenerated track selections.
 */
protected final void invalidate(){
  if (listener != null) {
    listener.onTrackSelectionsInvalidated();
  }
}
