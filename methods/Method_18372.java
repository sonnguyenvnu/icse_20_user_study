private boolean updateMountItemIfNeeded(LayoutOutput layoutOutput,LayoutState layoutState,MountItem currentMountItem,boolean useUpdateValueFromLayoutOutput,int componentTreeId,int index){
  final Component layoutOutputComponent=layoutOutput.getComponent();
  final Component itemComponent=currentMountItem.getComponent();
  if (layoutOutputComponent == null) {
    throw new RuntimeException("Trying to update a MountItem with a null Component.");
  }
  final boolean shouldUpdate=shouldUpdateMountItem(layoutOutput,currentMountItem,useUpdateValueFromLayoutOutput);
  final boolean shouldUpdateViewInfo=shouldUpdate || shouldUpdateViewInfo(layoutOutput,currentMountItem);
  if (shouldUpdate) {
    if (mLastMountedComponentTreeId != componentTreeId) {
      final Component component=currentMountItem.getComponent();
      if (isHostSpec(component)) {
        final ComponentHost componentHost=(ComponentHost)currentMountItem.getContent();
        removeDisappearingMountContentFromComponentHost(componentHost);
      }
    }
    maybeUnsetViewAttributes(currentMountItem);
    final ComponentHost host=currentMountItem.getHost();
    host.maybeUnregisterTouchExpansion(index,currentMountItem);
  }
 else   if (shouldUpdateViewInfo) {
    maybeUnsetViewAttributes(currentMountItem);
    final ComponentHost host=currentMountItem.getHost();
    host.maybeUnregisterTouchExpansion(index,currentMountItem);
  }
  if (currentMountItem.isBound()) {
    itemComponent.onUnbind(getContextForComponent(itemComponent),currentMountItem.getContent());
    currentMountItem.setIsBound(false);
  }
  currentMountItem.update(layoutOutput);
  if (shouldUpdate) {
    final ComponentHost host=currentMountItem.getHost();
    host.maybeRegisterTouchExpansion(index,currentMountItem);
    updateMountedContent(currentMountItem,layoutOutput,itemComponent);
    setViewAttributes(currentMountItem);
  }
 else   if (shouldUpdateViewInfo) {
    final ComponentHost host=currentMountItem.getHost();
    host.maybeRegisterTouchExpansion(index,currentMountItem);
    setViewAttributes(currentMountItem);
  }
  final Object currentContent=currentMountItem.getContent();
  bindComponentToContent(layoutOutputComponent,currentContent);
  currentMountItem.setIsBound(true);
  updateBoundsForMountedLayoutOutput(layoutOutput,layoutState,currentMountItem);
  maybeInvalidateAccessibilityState(currentMountItem);
  if (currentMountItem.getContent() instanceof Drawable) {
    maybeSetDrawableState(currentMountItem.getHost(),(Drawable)currentMountItem.getContent(),currentMountItem.getLayoutFlags(),currentMountItem.getNodeInfo());
  }
  return shouldUpdate;
}
