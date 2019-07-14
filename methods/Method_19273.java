@UiThread private void applyReadyBatches(){
  ThreadUtils.assertMainThread();
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("applyReadyBatches");
  }
  try {
    if (!mHasAsyncBatchesToCheck.get() || !mIsMeasured.get() || mIsInMeasure.get()) {
      mApplyReadyBatchesRetries=0;
      return;
    }
    if (mMountedView != null && mMountedView.isComputingLayout()) {
      mApplyReadyBatchesRetries++;
      if (mApplyReadyBatchesRetries > 100) {
        throw new RuntimeException("Too many retries -- RecyclerView is stuck in layout.");
      }
      ChoreographerCompatImpl.getInstance().postFrameCallback(mApplyReadyBatchesCallback);
      return;
    }
    mApplyReadyBatchesRetries=0;
    boolean appliedBatch=false;
    while (true) {
      final AsyncBatch batch;
synchronized (this) {
        if (mAsyncBatches.isEmpty()) {
          mHasAsyncBatchesToCheck.set(false);
          break;
        }
        batch=mAsyncBatches.peekFirst();
        if (!isBatchReady(batch)) {
          break;
        }
        mAsyncBatches.pollFirst();
      }
      applyBatch(batch);
      appliedBatch|=batch.mIsDataChanged;
    }
    if (appliedBatch) {
      maybeUpdateRangeOrRemeasureForMutation();
    }
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
}
