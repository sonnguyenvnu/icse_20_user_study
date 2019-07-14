/** 
 * @return true if this method did all the work that was necessary and there is no other contentthat needs mounting/unmounting in this mount step. If false then a full mount step should take place.
 */
private boolean performIncrementalMount(LayoutState layoutState,Rect localVisibleRect,boolean processVisibilityOutputs){
  if (mPreviousLocalVisibleRect.isEmpty()) {
    return false;
  }
  if (localVisibleRect.left != mPreviousLocalVisibleRect.left || localVisibleRect.right != mPreviousLocalVisibleRect.right) {
    return false;
  }
  final ArrayList<LayoutOutput> layoutOutputTops=layoutState.getMountableOutputTops();
  final ArrayList<LayoutOutput> layoutOutputBottoms=layoutState.getMountableOutputBottoms();
  final int count=layoutState.getMountableOutputCount();
  if (localVisibleRect.top > 0 || mPreviousLocalVisibleRect.top > 0) {
    while (mPreviousBottomsIndex < count && localVisibleRect.top >= layoutOutputBottoms.get(mPreviousBottomsIndex).getBounds().bottom) {
      final long id=layoutOutputBottoms.get(mPreviousBottomsIndex).getId();
      final int layoutOutputIndex=layoutState.getLayoutOutputPositionForId(id);
      if (!isAnimationLocked(layoutOutputIndex)) {
        unmountItem(layoutOutputIndex,mHostsByMarker);
      }
      mPreviousBottomsIndex++;
    }
    while (mPreviousBottomsIndex > 0 && localVisibleRect.top < layoutOutputBottoms.get(mPreviousBottomsIndex - 1).getBounds().bottom) {
      mPreviousBottomsIndex--;
      final LayoutOutput layoutOutput=layoutOutputBottoms.get(mPreviousBottomsIndex);
      final int layoutOutputIndex=layoutState.getLayoutOutputPositionForId(layoutOutput.getId());
      if (getItemAt(layoutOutputIndex) == null) {
        mountLayoutOutput(layoutState.getLayoutOutputPositionForId(layoutOutput.getId()),layoutOutput,layoutState);
        mComponentIdsMountedInThisFrame.add(layoutOutput.getId());
      }
    }
  }
  final int height=mLithoView.getHeight();
  if (localVisibleRect.bottom < height || mPreviousLocalVisibleRect.bottom < height) {
    while (mPreviousTopsIndex < count && localVisibleRect.bottom > layoutOutputTops.get(mPreviousTopsIndex).getBounds().top) {
      final LayoutOutput layoutOutput=layoutOutputTops.get(mPreviousTopsIndex);
      final int layoutOutputIndex=layoutState.getLayoutOutputPositionForId(layoutOutput.getId());
      if (getItemAt(layoutOutputIndex) == null) {
        mountLayoutOutput(layoutState.getLayoutOutputPositionForId(layoutOutput.getId()),layoutOutput,layoutState);
        mComponentIdsMountedInThisFrame.add(layoutOutput.getId());
      }
      mPreviousTopsIndex++;
    }
    while (mPreviousTopsIndex > 0 && localVisibleRect.bottom <= layoutOutputTops.get(mPreviousTopsIndex - 1).getBounds().top) {
      mPreviousTopsIndex--;
      final long id=layoutOutputTops.get(mPreviousTopsIndex).getId();
      final int layoutOutputIndex=layoutState.getLayoutOutputPositionForId(id);
      if (!isAnimationLocked(layoutOutputIndex)) {
        unmountItem(layoutOutputIndex,mHostsByMarker);
      }
    }
  }
  for (int i=0, size=mCanMountIncrementallyMountItems.size(); i < size; i++) {
    final MountItem mountItem=mCanMountIncrementallyMountItems.valueAt(i);
    final long layoutOutputId=mCanMountIncrementallyMountItems.keyAt(i);
    if (!mComponentIdsMountedInThisFrame.contains(layoutOutputId)) {
      final int layoutOutputPosition=layoutState.getLayoutOutputPositionForId(layoutOutputId);
      if (layoutOutputPosition != -1) {
        mountItemIncrementally(mountItem,processVisibilityOutputs);
      }
    }
  }
  mComponentIdsMountedInThisFrame.clear();
  return true;
}
