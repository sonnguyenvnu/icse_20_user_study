void startUnmountDisappearingItem(int index,MountItem mountItem){
  final Object content=mountItem.getContent();
  if (content instanceof Drawable) {
    ensureDrawableMountItems();
    ComponentHostUtils.removeItem(index,mDrawableMountItems,mScrapDrawableMountItems);
  }
 else   if (content instanceof View) {
    ensureViewMountItems();
    ComponentHostUtils.removeItem(index,mViewMountItems,mScrapViewMountItemsArray);
    mIsChildDrawingOrderDirty=true;
    maybeUnregisterTouchExpansion(index,mountItem);
  }
  ensureMountItems();
  ComponentHostUtils.removeItem(index,mMountItems,mScrapMountItemsArray);
  releaseScrapDataStructuresIfNeeded();
  ensureDisappearingItems();
  mDisappearingItems.add(mountItem);
}
