/** 
 * Removes and recycles all views - both those currently attached, and those in the Recycler.
 */
void removeAndRecycleViews(){
  if (mItemAnimator != null) {
    mItemAnimator.endAnimations();
  }
  if (mLayout != null) {
    mLayout.removeAndRecycleAllViews(mRecycler);
    mLayout.removeAndRecycleScrapInt(mRecycler);
  }
  mRecycler.clear();
}
