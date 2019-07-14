@ThreadConfined(UI) private void maybeDispatchDataRendered(){
  ThreadUtils.assertMainThread();
  if (mDataRenderedCallbacks.isEmpty()) {
    return;
  }
  if (!mIsInitMounted) {
    return;
  }
  if (mMountedView == null || !mMountedView.hasPendingAdapterUpdates() || !mMountedView.isAttachedToWindow() || !isVisibleToUser(mMountedView)) {
    final boolean isMounted=(mMountedView != null);
    final Deque<ChangeSetCompleteCallback> snapshotCallbacks=new ArrayDeque<>(mDataRenderedCallbacks);
    mDataRenderedCallbacks.clear();
    mMainThreadHandler.postAtFrontOfQueue(new Runnable(){
      @Override public void run(){
        final long uptimeMillis=SystemClock.uptimeMillis();
        while (!snapshotCallbacks.isEmpty()) {
          snapshotCallbacks.pollFirst().onDataRendered(isMounted,uptimeMillis);
        }
      }
    }
);
  }
 else {
    if (mDataRenderedCallbacks.size() > DATA_RENDERED_CALLBACKS_QUEUE_MAX_SIZE) {
      mDataRenderedCallbacks.clear();
      final ComponentsLogger logger=mComponentContext.getLogger();
      if (logger != null) {
        final StringBuilder messageBuilder=new StringBuilder();
        if (mMountedView == null) {
          messageBuilder.append("mMountedView == null");
        }
 else {
          messageBuilder.append("mMountedView: ").append(mMountedView).append(", hasPendingAdapterUpdates(): ").append(mMountedView.hasPendingAdapterUpdates()).append(", isAttachedToWindow(): ").append(mMountedView.isAttachedToWindow()).append(", getWindowVisibility(): ").append(mMountedView.getWindowVisibility()).append(", vie visible hierarchy: ").append(getVisibleHierarchy(mMountedView)).append(", getGlobalVisibleRect(): ").append(mMountedView.getGlobalVisibleRect(sDummyRect)).append(", isComputingLayout(): ").append(mMountedView.isComputingLayout());
        }
        messageBuilder.append(", visible range: [").append(mCurrentFirstVisiblePosition).append(", ").append(mCurrentLastVisiblePosition).append("]");
        logger.emitMessage(ComponentsLogger.LogLevel.ERROR,"@OnDataRendered callbacks aren't triggered as expected: " + messageBuilder);
      }
    }
  }
}
