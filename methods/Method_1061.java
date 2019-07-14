private synchronized void init(String id,Object callerContext){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("AbstractDraweeController#init");
  }
  mEventTracker.recordEvent(Event.ON_INIT_CONTROLLER);
  if (!mJustConstructed && mDeferredReleaser != null) {
    mDeferredReleaser.cancelDeferredRelease(this);
  }
  mIsAttached=false;
  mIsVisibleInViewportHint=false;
  releaseFetch();
  mRetainImageOnFailure=false;
  if (mRetryManager != null) {
    mRetryManager.init();
  }
  if (mGestureDetector != null) {
    mGestureDetector.init();
    mGestureDetector.setClickListener(this);
  }
  if (mControllerListener instanceof InternalForwardingListener) {
    ((InternalForwardingListener)mControllerListener).clearListeners();
  }
 else {
    mControllerListener=null;
  }
  mControllerViewportVisibilityListener=null;
  if (mSettableDraweeHierarchy != null) {
    mSettableDraweeHierarchy.reset();
    mSettableDraweeHierarchy.setControllerOverlay(null);
    mSettableDraweeHierarchy=null;
  }
  mControllerOverlay=null;
  if (FLog.isLoggable(FLog.VERBOSE)) {
    FLog.v(TAG,"controller %x %s -> %s: initialize",System.identityHashCode(this),mId,id);
  }
  mId=id;
  mCallerContext=callerContext;
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
}
