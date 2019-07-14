private void moveDrawableItem(MountItem item,int oldIndex,int newIndex){
  assertMainThread();
  ensureDrawableMountItems();
  if (mDrawableMountItems.get(newIndex) != null) {
    ensureScrapDrawableMountItemsArray();
    ComponentHostUtils.scrapItemAt(newIndex,mDrawableMountItems,mScrapDrawableMountItems);
  }
  ComponentHostUtils.moveItem(oldIndex,newIndex,mDrawableMountItems,mScrapDrawableMountItems);
  this.invalidate();
  releaseScrapDataStructuresIfNeeded();
}
