/** 
 * Go over all the mounted items from the leaves to the root and unmount only the items that are not present in the new LayoutOutputs. If an item is still present but in a new position move the item inside its host. The condition where an item changed host doesn't need any special treatment here since we mark them as removed and re-added when calculating the new LayoutOutputs
 */
private PrepareMountStats unmountOrMoveOldItems(LayoutState newLayoutState,List<Integer> disappearingItems){
  mPrepareMountStats.reset();
  if (mLayoutOutputsIds == null) {
    return mPrepareMountStats;
  }
  int disappearingItemsPointer=0;
  for (int i=0; i < mLayoutOutputsIds.length; i++) {
    final LayoutOutput newLayoutOutput=newLayoutState.getLayoutOutput(mLayoutOutputsIds[i]);
    final int newPosition=newLayoutOutput == null ? -1 : newLayoutOutput.getIndex();
    final MountItem oldItem=getItemAt(i);
    if (disappearingItems.size() > disappearingItemsPointer && disappearingItems.get(disappearingItemsPointer) == i) {
      i=disappearingItems.get(disappearingItemsPointer + 1);
      disappearingItemsPointer+=2;
      continue;
    }
    if (newPosition == -1) {
      unmountItem(i,mHostsByMarker);
      mPrepareMountStats.unmountedCount++;
    }
 else {
      final long newHostMarker=newLayoutOutput.getHostMarker();
      if (oldItem == null) {
        mPrepareMountStats.unmountedCount++;
      }
 else       if (oldItem.getHost() != mHostsByMarker.get(newHostMarker)) {
        unmountItem(i,mHostsByMarker);
        mPrepareMountStats.unmountedCount++;
      }
 else       if (newPosition != i) {
        oldItem.getHost().moveItem(oldItem,i,newPosition);
        mPrepareMountStats.movedCount++;
      }
 else {
        mPrepareMountStats.unchangedCount++;
      }
    }
  }
  return mPrepareMountStats;
}
