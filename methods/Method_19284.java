/** 
 * Inserts a new item at position. The  {@link RecyclerView} gets notified immediately about thenew item being inserted. If the item's position falls within the currently visible range, the layout is immediately computed on the] UiThread. The RenderInfo contains the component that will be inserted in the Binder and extra info like isSticky or spanCount.
 */
@UiThread public final void insertItemAt(int position,RenderInfo renderInfo){
  ThreadUtils.assertMainThread();
  assertNoInsertOperationIfCircular();
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") insertItemAt " + position + ", name: " + renderInfo.getName());
  }
  assertNotNullRenderInfo(renderInfo);
  final ComponentTreeHolder holder=createComponentTreeHolder(renderInfo);
synchronized (this) {
    if (mHasAsyncOperations) {
      throw new RuntimeException("Trying to do a sync insert when using asynchronous mutations!");
    }
    mComponentTreeHolders.add(position,holder);
    mRenderInfoViewCreatorController.maybeTrackViewCreator(renderInfo);
  }
  mInternalAdapter.notifyItemInserted(position);
  mViewportManager.setShouldUpdate(mViewportManager.insertAffectsVisibleRange(position,1,mEstimatedViewportCount));
}
