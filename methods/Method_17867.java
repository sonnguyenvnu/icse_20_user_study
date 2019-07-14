/** 
 * Moves the MountItem associated to oldIndex in the newIndex position. This happens when a LithoView needs to re-arrange the internal order of its items. If an item is already present in newIndex the item is guaranteed to be either unmounted or moved to a different index by subsequent calls to either  {@link ComponentHost#unmount(int,MountItem)} or{@link ComponentHost#moveItem(MountItem,int,int)}.
 * @param item The item that has been moved.
 * @param oldIndex The current index of the MountItem.
 * @param newIndex The new index of the MountItem.
 */
void moveItem(MountItem item,int oldIndex,int newIndex){
  if (item == null && mScrapMountItemsArray != null) {
    item=mScrapMountItemsArray.get(oldIndex);
  }
  if (item == null) {
    return;
  }
  maybeMoveTouchExpansionIndexes(item,oldIndex,newIndex);
  final Object content=item.getContent();
  ensureViewMountItems();
  if (content instanceof Drawable) {
    moveDrawableItem(item,oldIndex,newIndex);
  }
 else   if (content instanceof View) {
    mIsChildDrawingOrderDirty=true;
    startTemporaryDetach(((View)content));
    if (mViewMountItems.get(newIndex) != null) {
      ensureScrapViewMountItemsArray();
      ComponentHostUtils.scrapItemAt(newIndex,mViewMountItems,mScrapViewMountItemsArray);
    }
    ComponentHostUtils.moveItem(oldIndex,newIndex,mViewMountItems,mScrapViewMountItemsArray);
  }
  ensureMountItems();
  if (mMountItems.get(newIndex) != null) {
    ensureScrapMountItemsArray();
    ComponentHostUtils.scrapItemAt(newIndex,mMountItems,mScrapMountItemsArray);
  }
  ComponentHostUtils.moveItem(oldIndex,newIndex,mMountItems,mScrapMountItemsArray);
  releaseScrapDataStructuresIfNeeded();
  if (content instanceof View) {
    finishTemporaryDetach(((View)content));
  }
}
