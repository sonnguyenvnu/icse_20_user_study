/** 
 * If necessary, layouts new items for predictive animations
 */
private void layoutForPredictiveAnimations(RecyclerView.Recycler recycler,RecyclerView.State state,int startOffset,int endOffset){
  if (!state.willRunPredictiveAnimations() || getChildCount() == 0 || state.isPreLayout() || !supportsPredictiveItemAnimations()) {
    return;
  }
  int scrapExtraStart=0, scrapExtraEnd=0;
  final List<RecyclerView.ViewHolder> scrapList=recycler.getScrapList();
  final int scrapSize=scrapList.size();
  final int firstChildPos=getPosition(getChildAt(0));
  for (int i=0; i < scrapSize; i++) {
    RecyclerView.ViewHolder scrap=scrapList.get(i);
    if (scrap.isRemoved()) {
      continue;
    }
    final int position=scrap.getLayoutPosition();
    final int direction=position < firstChildPos != mShouldReverseLayout ? LayoutState.LAYOUT_START : LayoutState.LAYOUT_END;
    if (direction == LayoutState.LAYOUT_START) {
      scrapExtraStart+=mOrientationHelper.getDecoratedMeasurement(scrap.itemView);
    }
 else {
      scrapExtraEnd+=mOrientationHelper.getDecoratedMeasurement(scrap.itemView);
    }
  }
  if (DEBUG) {
    Log.d(TAG,"for unused scrap, decided to add " + scrapExtraStart + " towards start and " + scrapExtraEnd + " towards end");
  }
  mLayoutState.mScrapList=scrapList;
  if (scrapExtraStart > 0) {
    View anchor=getChildClosestToStart();
    updateLayoutStateToFillStart(getPosition(anchor),startOffset);
    mLayoutState.mExtraFillSpace=scrapExtraStart;
    mLayoutState.mAvailable=0;
    mLayoutState.assignPositionFromScrapList();
    fill(recycler,mLayoutState,state,false);
  }
  if (scrapExtraEnd > 0) {
    View anchor=getChildClosestToEnd();
    updateLayoutStateToFillEnd(getPosition(anchor),endOffset);
    mLayoutState.mExtraFillSpace=scrapExtraEnd;
    mLayoutState.mAvailable=0;
    mLayoutState.assignPositionFromScrapList();
    fill(recycler,mLayoutState,state,false);
  }
  mLayoutState.mScrapList=null;
}
