@GuardedBy("this") private void maybeFillHScrollViewport(){
  if (!mHScrollAsyncMode || mHasFilledViewport) {
    return;
  }
  mCommitPolicy=CommitPolicy.LAYOUT_BEFORE_INSERT;
  if (ThreadUtils.isMainThread()) {
    applyReadyBatches();
  }
 else {
    if (!mComponentTreeHolders.isEmpty()) {
      fillListViewport(mMeasuredSize.width,mMeasuredSize.height,null);
    }
 else     if (!mAsyncBatches.isEmpty()) {
      List<ComponentTreeHolder> insertsInFirstBatch=new ArrayList<>();
      for (      AsyncOperation operation : mAsyncBatches.getFirst().mOperations) {
        if (operation instanceof AsyncInsertOperation) {
          insertsInFirstBatch.add(((AsyncInsertOperation)operation).mHolder);
        }
      }
      computeLayoutsToFillListViewport(insertsInFirstBatch,0,mMeasuredSize.width,mMeasuredSize.height,null);
    }
    ChoreographerCompatImpl.getInstance().postFrameCallback(mApplyReadyBatchesCallback);
  }
  mHasFilledViewport=true;
}
