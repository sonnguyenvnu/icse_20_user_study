/** 
 * Updates the range of items starting at position. The  {@link RecyclerView} gets notifiedimmediately about the item being updated.
 */
@UiThread public final void updateRangeAt(int position,List<RenderInfo> renderInfos){
  ThreadUtils.assertMainThread();
  if (SectionsDebug.ENABLED) {
    final String[] names=new String[renderInfos.size()];
    for (int i=0; i < renderInfos.size(); i++) {
      names[i]=renderInfos.get(i).getName();
    }
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") updateRangeAt " + position + ", size: " + renderInfos.size() + ", names: " + Arrays.toString(names));
  }
synchronized (this) {
    for (int i=0, size=renderInfos.size(); i < size; i++) {
      final ComponentTreeHolder holder=mComponentTreeHolders.get(position + i);
      final RenderInfo newRenderInfo=renderInfos.get(i);
      assertNotNullRenderInfo(newRenderInfo);
      if (newRenderInfo.rendersView() || holder.getRenderInfo().rendersView()) {
        mInternalAdapter.notifyItemChanged(position + i);
      }
      mRenderInfoViewCreatorController.maybeTrackViewCreator(newRenderInfo);
      updateHolder(holder,newRenderInfo);
    }
  }
  mViewportManager.setShouldUpdate(mViewportManager.updateAffectsVisibleRange(position,renderInfos.size()));
}
