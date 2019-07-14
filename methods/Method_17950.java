/** 
 * Provide custom  {@link LithoHandler}. If null is provided default one will be used for layouts.
 */
@ThreadConfined(ThreadConfined.UI) public void updateLayoutThreadHandler(@Nullable LithoHandler layoutThreadHandler){
synchronized (mUpdateStateSyncRunnableLock) {
    if (mUpdateStateSyncRunnable != null) {
      mLayoutThreadHandler.remove(mUpdateStateSyncRunnable);
    }
  }
synchronized (mCurrentCalculateLayoutRunnableLock) {
    if (mCurrentCalculateLayoutRunnable != null) {
      mLayoutThreadHandler.remove(mCurrentCalculateLayoutRunnable);
    }
  }
  mLayoutThreadHandler=ensureAndInstrumentLayoutThreadHandler(layoutThreadHandler);
}
