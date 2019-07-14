/** 
 * Transfer mBackgroundLayoutState to mMainThreadLayoutState. This will proxy to the main thread if necessary. If the component/size-spec changes in the meantime, then the transfer will be aborted.
 */
private void postBackgroundLayoutStateUpdated(){
  if (isMainThread()) {
    backgroundLayoutStateUpdated();
  }
 else {
    String tag=EMPTY_STRING;
    if (mMainThreadHandler.isTracing()) {
      tag="postBackgroundLayoutStateUpdated";
    }
    mMainThreadHandler.post(mBackgroundLayoutStateUpdateRunnable,tag);
  }
}
