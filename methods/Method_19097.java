@ThreadConfined(ThreadConfined.ANY) private void applyChangeSetsToTargetBackgroundAllowed(int source,@Nullable String attribution,@Nullable Section oldSection){
  if (!mUseBackgroundChangeSets) {
    throw new IllegalStateException("Must use UIThread-only variant when background change sets are not enabled.");
  }
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("applyChangeSetsToTargetBackgroundAllowed");
  }
  try {
synchronized (this) {
      if (mReleased) {
        return;
      }
      applyChangeSetsToTargetUnchecked(mCurrentSection,oldSection,mPendingChangeSets,source,attribution);
      mPendingChangeSets.clear();
    }
    if (isMainThread()) {
      maybeDispatchFocusRequests();
    }
 else {
      String tag=EMPTY_STRING;
      if (mMainThreadHandler.isTracing()) {
        tag="SectionTree.applyChangeSetsToTargetBackgroundAllowed - " + mTag;
      }
      mMainThreadHandler.post(new Runnable(){
        @Override public void run(){
          maybeDispatchFocusRequests();
        }
      }
,tag);
    }
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
}
