/** 
 * Moves an item from fromPosition to toPosition. If the new position of the item is within the currently visible range, a layout is calculated immediately on the UI Thread.
 */
@UiThread public final void moveItem(int fromPosition,int toPosition){
  ThreadUtils.assertMainThread();
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + hashCode() + ") moveItem " + fromPosition + " to " + toPosition);
  }
  final ComponentTreeHolder holder;
  final boolean isNewPositionInRange;
synchronized (this) {
    holder=mComponentTreeHolders.remove(fromPosition);
    mComponentTreeHolders.add(toPosition,holder);
    isNewPositionInRange=mEstimatedViewportCount != UNSET && toPosition >= mCurrentFirstVisiblePosition - (mEstimatedViewportCount * mRangeRatio) && toPosition <= mCurrentFirstVisiblePosition + mEstimatedViewportCount + (mEstimatedViewportCount * mRangeRatio);
  }
  final boolean isTreeValid=holder.isTreeValid();
  if (isTreeValid && !isNewPositionInRange) {
    holder.acquireStateAndReleaseTree();
  }
  mInternalAdapter.notifyItemMoved(fromPosition,toPosition);
  mViewportManager.setShouldUpdate(mViewportManager.moveAffectsVisibleRange(fromPosition,toPosition,mEstimatedViewportCount));
}
