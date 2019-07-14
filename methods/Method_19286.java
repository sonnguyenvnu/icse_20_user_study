/** 
 * Inserts the new items starting from position. The  {@link RecyclerView} gets notifiedimmediately about the new item being inserted. The RenderInfo contains the component that will be inserted in the Binder and extra info like isSticky or spanCount.
 */
@UiThread public final void insertRangeAt(int position,List<RenderInfo> renderInfos){
  ThreadUtils.assertMainThread();
  assertNoInsertOperationIfCircular();
  if (SectionsDebug.ENABLED) {
    final String[] names=new String[renderInfos.size()];
    for (int i=0; i < renderInfos.size(); i++) {
      names[i]=renderInfos.get(i).getName();
    }
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") insertRangeAt " + position + ", size: " + renderInfos.size() + ", names: " + Arrays.toString(names));
  }
synchronized (this) {
    for (int i=0, size=renderInfos.size(); i < size; i++) {
      final RenderInfo renderInfo=renderInfos.get(i);
      assertNotNullRenderInfo(renderInfo);
      final ComponentTreeHolder holder=createComponentTreeHolder(renderInfo);
      if (mHasAsyncOperations) {
        throw new RuntimeException("Trying to do a sync insert when using asynchronous mutations!");
      }
      mComponentTreeHolders.add(position + i,holder);
      mRenderInfoViewCreatorController.maybeTrackViewCreator(renderInfo);
    }
  }
  mInternalAdapter.notifyItemRangeInserted(position,renderInfos.size());
  mViewportManager.setShouldUpdate(mViewportManager.insertAffectsVisibleRange(position,renderInfos.size(),mEstimatedViewportCount));
}
