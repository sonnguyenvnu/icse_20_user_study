/** 
 * Replaces all items in the  {@link RecyclerBinder} with the provided {@link RenderInfo}s. 
 */
@UiThread public final void replaceAll(List<RenderInfo> renderInfos){
  final List<ComponentTreeHolder> toRelease;
synchronized (this) {
    if (mHasAsyncOperations) {
      throw new RuntimeException("Trying to do a sync replaceAll when using asynchronous mutations!");
    }
    toRelease=new ArrayList<>(mComponentTreeHolders);
    mComponentTreeHolders.clear();
    for (    RenderInfo renderInfo : renderInfos) {
      mComponentTreeHolders.add(createComponentTreeHolder(renderInfo));
    }
  }
  mInternalAdapter.notifyDataSetChanged();
  mViewportManager.setShouldUpdate(true);
  if (ComponentsConfiguration.isReleaseComponentTreeInRecyclerBinder) {
    postReleaseComponentTreeHolders(toRelease);
  }
}
