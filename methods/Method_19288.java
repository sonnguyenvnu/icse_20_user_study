/** 
 * Updates the item at position. The  {@link RecyclerView} gets notified immediately about the itembeing updated. If the item's position falls within the currently visible range, the layout is immediately computed on the UiThread.
 */
@UiThread public final void updateItemAt(int position,RenderInfo renderInfo){
  ThreadUtils.assertMainThread();
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") updateItemAt " + position + ", name: " + renderInfo.getName());
  }
  final ComponentTreeHolder holder;
  final boolean renderInfoWasView;
synchronized (this) {
    holder=mComponentTreeHolders.get(position);
    renderInfoWasView=holder.getRenderInfo().rendersView();
    assertNotNullRenderInfo(renderInfo);
    mRenderInfoViewCreatorController.maybeTrackViewCreator(renderInfo);
    updateHolder(holder,renderInfo);
  }
  if (renderInfoWasView || renderInfo.rendersView()) {
    mInternalAdapter.notifyItemChanged(position);
  }
  mViewportManager.setShouldUpdate(mViewportManager.updateAffectsVisibleRange(position,1));
}
