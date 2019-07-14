private void unmountItem(int index,LongSparseArray<ComponentHost> hostsByMarker){
  final MountItem item=getItemAt(index);
  final long startTime=System.nanoTime();
  if (item == null) {
    return;
  }
  if (mLayoutOutputsIds[index] == ROOT_HOST_ID) {
    unsetViewAttributes(item,true);
    return;
  }
  final Object content=item.getContent();
  if ((content instanceof ComponentHost) && !(content instanceof LithoView)) {
    final ComponentHost host=(ComponentHost)content;
    for (int i=host.getMountItemCount() - 1; i >= 0; i--) {
      final MountItem mountItem=host.getMountItemAt(i);
      final long layoutOutputId=mIndexToItemMap.keyAt(mIndexToItemMap.indexOfValue(mountItem));
      for (int mountIndex=mLayoutOutputsIds.length - 1; mountIndex >= 0; mountIndex--) {
        if (mLayoutOutputsIds[mountIndex] == layoutOutputId) {
          unmountItem(mountIndex,hostsByMarker);
          break;
        }
      }
    }
    if (host.getMountItemCount() > 0) {
      throw new IllegalStateException("Recursively unmounting items from a ComponentHost, left" + " some items behind maybe because not tracked by its MountState");
    }
  }
  if (content instanceof HasLithoViewChildren) {
    final ArrayList<LithoView> lithoViews=new ArrayList<>();
    ((HasLithoViewChildren)content).obtainLithoViewChildren(lithoViews);
    for (int i=lithoViews.size() - 1; i >= 0; i--) {
      final LithoView lithoView=lithoViews.get(i);
      lithoView.unmountAllItems();
    }
  }
  final ComponentHost host=item.getHost();
  host.unmount(index,item);
  maybeUnsetViewAttributes(item);
  final Component component=item.getComponent();
  if (isHostSpec(component)) {
    final ComponentHost componentHost=(ComponentHost)content;
    hostsByMarker.removeAt(hostsByMarker.indexOfValue(componentHost));
    removeDisappearingMountContentFromComponentHost(componentHost);
  }
  unbindAndUnmountLifecycle(item);
  final long layoutOutputId=mLayoutOutputsIds[index];
  mIndexToItemMap.remove(layoutOutputId);
  if (item.hasTransitionId()) {
    final @OutputUnitType int type=LayoutStateOutputIdCalculator.getTypeFromId(layoutOutputId);
    maybeRemoveAnimatingMountContent(item.getTransitionId(),type);
  }
  if (component.hasChildLithoViews()) {
    mCanMountIncrementallyMountItems.delete(mLayoutOutputsIds[index]);
  }
  final String rootComponentName=mLastMountedLayoutState == null ? "null_layout" : mLastMountedLayoutState.mRootComponentName;
  if (!(isHostSpec(component) && ComponentHostRecycleUtil.shouldSkipRecyclingComponentHost(index,rootComponentName))) {
    item.releaseMountContent(mContext.getAndroidContext());
  }
  if (mMountStats.isLoggingEnabled) {
    mMountStats.unmountedTimes.add((System.nanoTime() - startTime) / NS_IN_MS);
    mMountStats.unmountedNames.add(component.getSimpleName());
    mMountStats.unmountedCount++;
  }
}
