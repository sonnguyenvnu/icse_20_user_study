/** 
 * Updates timeline and manifest and notifies all listeners of the update.
 * @param timeline The new {@link Timeline}.
 * @param manifest The new manifest. May be null.
 */
protected final void refreshSourceInfo(Timeline timeline,@Nullable Object manifest){
  this.timeline=timeline;
  this.manifest=manifest;
  for (  SourceInfoRefreshListener listener : sourceInfoListeners) {
    listener.onSourceInfoRefreshed(this,timeline,manifest);
  }
}
