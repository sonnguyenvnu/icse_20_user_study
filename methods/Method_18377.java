/** 
 * Takes care of disappearing items from the last mounted layout (re-mounts them to the root if needed, starts disappearing, removes them from mapping). Returns the list of ids, which for every disappearing subtree contains a pair [index of the root of the subtree, index of the last descendant of that subtree]
 */
private List<Integer> extractDisappearingItems(LayoutState newLayoutState){
  if (mLayoutOutputsIds == null) {
    return Collections.emptyList();
  }
  List<Integer> indices=null;
  int index=0;
  while (index < mLayoutOutputsIds.length) {
    if (isItemDisappearing(newLayoutState,index)) {
      final int lastDescendantIndex=findLastDescendantIndex(mLastMountedLayoutState,index);
      for (int j=index; j <= lastDescendantIndex; j++) {
        final MountItem mountedItem=getItemAt(j);
        if (mountedItem != null) {
          continue;
        }
        final LayoutOutput layoutOutput=mLastMountedLayoutState.getMountableOutputAt(j);
        mountLayoutOutput(j,layoutOutput,mLastMountedLayoutState);
      }
      final MountItem disappearingItem=getItemAt(index);
      remountComponentHostToRootIfNeeded(index);
      removeDisappearingItemMappings(index,lastDescendantIndex);
      startUnmountDisappearingItem(disappearingItem,index);
      if (indices == null) {
        indices=new ArrayList<>(2);
      }
      indices.add(index);
      indices.add(lastDescendantIndex);
      index=lastDescendantIndex + 1;
    }
 else {
      index++;
    }
  }
  return indices != null ? indices : Collections.<Integer>emptyList();
}
