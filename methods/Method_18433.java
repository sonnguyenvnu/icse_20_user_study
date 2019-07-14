private void endUnmountDisappearingItem(OutputUnitsAffinityGroup<MountItem> group){
  maybeRemoveAnimatingMountContent(group.getMostSignificantUnit().getTransitionId());
  for (int i=0, size=group.size(); i < size; i++) {
    final MountItem item=group.getAt(i);
    if (group.typeAt(i) == OutputUnitType.HOST) {
      final ComponentHost content=(ComponentHost)item.getContent();
      for (int j=content.getMountItemCount() - 1; j >= 0; j--) {
        final MountItem mountItem=content.getMountItemAt(j);
        unmountDisappearingItemChild(mContext,mountItem);
      }
      if (content.getMountItemCount() > 0) {
        throw new IllegalStateException("Recursively unmounting items from a ComponentHost, left" + " some items behind maybe because not tracked by its MountState");
      }
    }
    final ComponentHost host=item.getHost();
    host.unmountDisappearingItem(item);
    maybeUnsetViewAttributes(item);
    unbindAndUnmountLifecycle(item);
    if (item.getComponent().hasChildLithoViews()) {
      final int index=mCanMountIncrementallyMountItems.indexOfValue(item);
      if (index > 0) {
        mCanMountIncrementallyMountItems.removeAt(index);
      }
    }
    assertNoDanglingMountContent(item);
    item.releaseMountContent(mContext.getAndroidContext());
  }
}
