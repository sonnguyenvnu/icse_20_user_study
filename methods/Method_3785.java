@Override void layoutChunk(RecyclerView.Recycler recycler,RecyclerView.State state,LayoutState layoutState,LayoutChunkResult result){
  final int otherDirSpecMode=mOrientationHelper.getModeInOther();
  final boolean flexibleInOtherDir=otherDirSpecMode != View.MeasureSpec.EXACTLY;
  final int currentOtherDirSize=getChildCount() > 0 ? mCachedBorders[mSpanCount] : 0;
  if (flexibleInOtherDir) {
    updateMeasurements();
  }
  final boolean layingOutInPrimaryDirection=layoutState.mItemDirection == LayoutState.ITEM_DIRECTION_TAIL;
  int count=0;
  int consumedSpanCount=0;
  int remainingSpan=mSpanCount;
  if (!layingOutInPrimaryDirection) {
    int itemSpanIndex=getSpanIndex(recycler,state,layoutState.mCurrentPosition);
    int itemSpanSize=getSpanSize(recycler,state,layoutState.mCurrentPosition);
    remainingSpan=itemSpanIndex + itemSpanSize;
  }
  while (count < mSpanCount && layoutState.hasMore(state) && remainingSpan > 0) {
    int pos=layoutState.mCurrentPosition;
    final int spanSize=getSpanSize(recycler,state,pos);
    if (spanSize > mSpanCount) {
      throw new IllegalArgumentException("Item at position " + pos + " requires " + spanSize + " spans but GridLayoutManager has only " + mSpanCount + " spans.");
    }
    remainingSpan-=spanSize;
    if (remainingSpan < 0) {
      break;
    }
    View view=layoutState.next(recycler);
    if (view == null) {
      break;
    }
    consumedSpanCount+=spanSize;
    mSet[count]=view;
    count++;
  }
  if (count == 0) {
    result.mFinished=true;
    return;
  }
  int maxSize=0;
  float maxSizeInOther=0;
  assignSpans(recycler,state,count,layingOutInPrimaryDirection);
  for (int i=0; i < count; i++) {
    View view=mSet[i];
    if (layoutState.mScrapList == null) {
      if (layingOutInPrimaryDirection) {
        addView(view);
      }
 else {
        addView(view,0);
      }
    }
 else {
      if (layingOutInPrimaryDirection) {
        addDisappearingView(view);
      }
 else {
        addDisappearingView(view,0);
      }
    }
    calculateItemDecorationsForChild(view,mDecorInsets);
    measureChild(view,otherDirSpecMode,false);
    final int size=mOrientationHelper.getDecoratedMeasurement(view);
    if (size > maxSize) {
      maxSize=size;
    }
    final LayoutParams lp=(LayoutParams)view.getLayoutParams();
    final float otherSize=1f * mOrientationHelper.getDecoratedMeasurementInOther(view) / lp.mSpanSize;
    if (otherSize > maxSizeInOther) {
      maxSizeInOther=otherSize;
    }
  }
  if (flexibleInOtherDir) {
    guessMeasurement(maxSizeInOther,currentOtherDirSize);
    maxSize=0;
    for (int i=0; i < count; i++) {
      View view=mSet[i];
      measureChild(view,View.MeasureSpec.EXACTLY,true);
      final int size=mOrientationHelper.getDecoratedMeasurement(view);
      if (size > maxSize) {
        maxSize=size;
      }
    }
  }
  for (int i=0; i < count; i++) {
    final View view=mSet[i];
    if (mOrientationHelper.getDecoratedMeasurement(view) != maxSize) {
      final LayoutParams lp=(LayoutParams)view.getLayoutParams();
      final Rect decorInsets=lp.mDecorInsets;
      final int verticalInsets=decorInsets.top + decorInsets.bottom + lp.topMargin + lp.bottomMargin;
      final int horizontalInsets=decorInsets.left + decorInsets.right + lp.leftMargin + lp.rightMargin;
      final int totalSpaceInOther=getSpaceForSpanRange(lp.mSpanIndex,lp.mSpanSize);
      final int wSpec;
      final int hSpec;
      if (mOrientation == VERTICAL) {
        wSpec=getChildMeasureSpec(totalSpaceInOther,View.MeasureSpec.EXACTLY,horizontalInsets,lp.width,false);
        hSpec=View.MeasureSpec.makeMeasureSpec(maxSize - verticalInsets,View.MeasureSpec.EXACTLY);
      }
 else {
        wSpec=View.MeasureSpec.makeMeasureSpec(maxSize - horizontalInsets,View.MeasureSpec.EXACTLY);
        hSpec=getChildMeasureSpec(totalSpaceInOther,View.MeasureSpec.EXACTLY,verticalInsets,lp.height,false);
      }
      measureChildWithDecorationsAndMargin(view,wSpec,hSpec,true);
    }
  }
  result.mConsumed=maxSize;
  int left=0, right=0, top=0, bottom=0;
  if (mOrientation == VERTICAL) {
    if (layoutState.mLayoutDirection == LayoutState.LAYOUT_START) {
      bottom=layoutState.mOffset;
      top=bottom - maxSize;
    }
 else {
      top=layoutState.mOffset;
      bottom=top + maxSize;
    }
  }
 else {
    if (layoutState.mLayoutDirection == LayoutState.LAYOUT_START) {
      right=layoutState.mOffset;
      left=right - maxSize;
    }
 else {
      left=layoutState.mOffset;
      right=left + maxSize;
    }
  }
  for (int i=0; i < count; i++) {
    View view=mSet[i];
    LayoutParams params=(LayoutParams)view.getLayoutParams();
    if (mOrientation == VERTICAL) {
      if (isLayoutRTL()) {
        right=getPaddingLeft() + mCachedBorders[mSpanCount - params.mSpanIndex];
        left=right - mOrientationHelper.getDecoratedMeasurementInOther(view);
      }
 else {
        left=getPaddingLeft() + mCachedBorders[params.mSpanIndex];
        right=left + mOrientationHelper.getDecoratedMeasurementInOther(view);
      }
    }
 else {
      top=getPaddingTop() + mCachedBorders[params.mSpanIndex];
      bottom=top + mOrientationHelper.getDecoratedMeasurementInOther(view);
    }
    layoutDecoratedWithMargins(view,left,top,right,bottom);
    if (DEBUG) {
      Log.d(TAG,"laid out child at position " + getPosition(view) + ", with l:" + (left + params.leftMargin) + ", t:" + (top + params.topMargin) + ", r:" + (right - params.rightMargin) + ", b:" + (bottom - params.bottomMargin) + ", span:" + params.mSpanIndex + ", spanSize:" + params.mSpanSize);
    }
    if (params.isItemRemoved() || params.isItemChanged()) {
      result.mIgnoreConsumed=true;
    }
    result.mFocusable|=view.hasFocusable();
  }
  Arrays.fill(mSet,null);
}
