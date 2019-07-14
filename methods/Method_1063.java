/** 
 * Sets the hierarchy. <p>The controller should be detached when this method is called.
 * @param hierarchy This must be an instance of {@link SettableDraweeHierarchy}
 */
@Override public void setHierarchy(@Nullable DraweeHierarchy hierarchy){
  if (FLog.isLoggable(FLog.VERBOSE)) {
    FLog.v(TAG,"controller %x %s: setHierarchy: %s",System.identityHashCode(this),mId,hierarchy);
  }
  mEventTracker.recordEvent((hierarchy != null) ? Event.ON_SET_HIERARCHY : Event.ON_CLEAR_HIERARCHY);
  if (mIsRequestSubmitted) {
    mDeferredReleaser.cancelDeferredRelease(this);
    release();
  }
  if (mSettableDraweeHierarchy != null) {
    mSettableDraweeHierarchy.setControllerOverlay(null);
    mSettableDraweeHierarchy=null;
  }
  if (hierarchy != null) {
    Preconditions.checkArgument(hierarchy instanceof SettableDraweeHierarchy);
    mSettableDraweeHierarchy=(SettableDraweeHierarchy)hierarchy;
    mSettableDraweeHierarchy.setControllerOverlay(mControllerOverlay);
  }
}
