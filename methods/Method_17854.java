/** 
 * Unmounts the given  {@link MountItem} with unique index.
 * @param index index of the {@link MountItem}. Guaranteed to be the same index as was passed for the corresponding  {@code mount(index, mountItem)} call.
 * @param mountItem item to be unmounted from the host.
 */
public void unmount(int index,MountItem mountItem){
  final Object content=mountItem.getContent();
  if (content instanceof Drawable) {
    ensureDrawableMountItems();
    unmountDrawable(mountItem);
    ComponentHostUtils.removeItem(index,mDrawableMountItems,mScrapDrawableMountItems);
  }
 else   if (content instanceof View) {
    unmountView((View)content);
    ensureViewMountItems();
    ComponentHostUtils.removeItem(index,mViewMountItems,mScrapViewMountItemsArray);
    mIsChildDrawingOrderDirty=true;
    maybeUnregisterTouchExpansion(index,mountItem);
  }
  ensureMountItems();
  ComponentHostUtils.removeItem(index,mMountItems,mScrapMountItemsArray);
  releaseScrapDataStructuresIfNeeded();
  maybeInvalidateAccessibilityState(mountItem);
}
