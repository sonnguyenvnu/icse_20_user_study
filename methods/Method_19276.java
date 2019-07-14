@GuardedBy("this") @UiThread private void applyAsyncInsert(AsyncInsertOperation operation){
  if (operation.mHolder.isInserted()) {
    return;
  }
  mComponentTreeHolders.add(operation.mPosition,operation.mHolder);
  operation.mHolder.setInserted(true);
  mInternalAdapter.notifyItemInserted(operation.mPosition);
  mViewportManager.insertAffectsVisibleRange(operation.mPosition,1,mEstimatedViewportCount);
}
