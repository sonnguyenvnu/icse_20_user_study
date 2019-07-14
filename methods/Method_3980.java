/** 
 * Records the animation information for a view holder that was bounced from hidden list. It also clears the bounce back flag.
 */
void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewHolder,ItemHolderInfo animationInfo){
  viewHolder.setFlags(0,ViewHolder.FLAG_BOUNCED_FROM_HIDDEN_LIST);
  if (mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
    long key=getChangedHolderKey(viewHolder);
    mViewInfoStore.addToOldChangeHolders(key,viewHolder);
  }
  mViewInfoStore.addToPreLayout(viewHolder,animationInfo);
}
