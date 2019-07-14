private int fill(RecyclerView.Recycler recycler,LayoutState layoutState,RecyclerView.State state){
  mRemainingSpans.set(0,mSpanCount,true);
  final int targetLine;
  if (mLayoutState.mInfinite) {
    if (layoutState.mLayoutDirection == LayoutState.LAYOUT_END) {
      targetLine=Integer.MAX_VALUE;
    }
 else {
      targetLine=Integer.MIN_VALUE;
    }
  }
 else {
    if (layoutState.mLayoutDirection == LayoutState.LAYOUT_END) {
      targetLine=layoutState.mEndLine + layoutState.mAvailable;
    }
 else {
      targetLine=layoutState.mStartLine - layoutState.mAvailable;
    }
  }
  updateAllRemainingSpans(layoutState.mLayoutDirection,targetLine);
  if (DEBUG) {
    Log.d(TAG,"FILLING targetLine: " + targetLine + "," + "remaining spans:" + mRemainingSpans + ", state: " + layoutState);
  }
  final int defaultNewViewLine=mShouldReverseLayout ? mPrimaryOrientation.getEndAfterPadding() : mPrimaryOrientation.getStartAfterPadding();
  boolean added=false;
  while (layoutState.hasMore(state) && (mLayoutState.mInfinite || !mRemainingSpans.isEmpty())) {
    View view=layoutState.next(recycler);
    LayoutParams lp=((LayoutParams)view.getLayoutParams());
    final int position=lp.getViewLayoutPosition();
    final int spanIndex=mLazySpanLookup.getSpan(position);
    Span currentSpan;
    final boolean assignSpan=spanIndex == LayoutParams.INVALID_SPAN_ID;
    if (assignSpan) {
      currentSpan=lp.mFullSpan ? mSpans[0] : getNextSpan(layoutState);
      mLazySpanLookup.setSpan(position,currentSpan);
      if (DEBUG) {
        Log.d(TAG,"assigned " + currentSpan.mIndex + " for " + position);
      }
    }
 else {
      if (DEBUG) {
        Log.d(TAG,"using " + spanIndex + " for pos " + position);
      }
      currentSpan=mSpans[spanIndex];
    }
    lp.mSpan=currentSpan;
    if (layoutState.mLayoutDirection == LayoutState.LAYOUT_END) {
      addView(view);
    }
 else {
      addView(view,0);
    }
    measureChildWithDecorationsAndMargin(view,lp,false);
    final int start;
    final int end;
    if (layoutState.mLayoutDirection == LayoutState.LAYOUT_END) {
      start=lp.mFullSpan ? getMaxEnd(defaultNewViewLine) : currentSpan.getEndLine(defaultNewViewLine);
      end=start + mPrimaryOrientation.getDecoratedMeasurement(view);
      if (assignSpan && lp.mFullSpan) {
        LazySpanLookup.FullSpanItem fullSpanItem;
        fullSpanItem=createFullSpanItemFromEnd(start);
        fullSpanItem.mGapDir=LayoutState.LAYOUT_START;
        fullSpanItem.mPosition=position;
        mLazySpanLookup.addFullSpanItem(fullSpanItem);
      }
    }
 else {
      end=lp.mFullSpan ? getMinStart(defaultNewViewLine) : currentSpan.getStartLine(defaultNewViewLine);
      start=end - mPrimaryOrientation.getDecoratedMeasurement(view);
      if (assignSpan && lp.mFullSpan) {
        LazySpanLookup.FullSpanItem fullSpanItem;
        fullSpanItem=createFullSpanItemFromStart(end);
        fullSpanItem.mGapDir=LayoutState.LAYOUT_END;
        fullSpanItem.mPosition=position;
        mLazySpanLookup.addFullSpanItem(fullSpanItem);
      }
    }
    if (lp.mFullSpan && layoutState.mItemDirection == LayoutState.ITEM_DIRECTION_HEAD) {
      if (assignSpan) {
        mLaidOutInvalidFullSpan=true;
      }
 else {
        final boolean hasInvalidGap;
        if (layoutState.mLayoutDirection == LayoutState.LAYOUT_END) {
          hasInvalidGap=!areAllEndsEqual();
        }
 else {
          hasInvalidGap=!areAllStartsEqual();
        }
        if (hasInvalidGap) {
          final LazySpanLookup.FullSpanItem fullSpanItem=mLazySpanLookup.getFullSpanItem(position);
          if (fullSpanItem != null) {
            fullSpanItem.mHasUnwantedGapAfter=true;
          }
          mLaidOutInvalidFullSpan=true;
        }
      }
    }
    attachViewToSpans(view,lp,layoutState);
    final int otherStart;
    final int otherEnd;
    if (isLayoutRTL() && mOrientation == VERTICAL) {
      otherEnd=lp.mFullSpan ? mSecondaryOrientation.getEndAfterPadding() : mSecondaryOrientation.getEndAfterPadding() - (mSpanCount - 1 - currentSpan.mIndex) * mSizePerSpan;
      otherStart=otherEnd - mSecondaryOrientation.getDecoratedMeasurement(view);
    }
 else {
      otherStart=lp.mFullSpan ? mSecondaryOrientation.getStartAfterPadding() : currentSpan.mIndex * mSizePerSpan + mSecondaryOrientation.getStartAfterPadding();
      otherEnd=otherStart + mSecondaryOrientation.getDecoratedMeasurement(view);
    }
    if (mOrientation == VERTICAL) {
      layoutDecoratedWithMargins(view,otherStart,start,otherEnd,end);
    }
 else {
      layoutDecoratedWithMargins(view,start,otherStart,end,otherEnd);
    }
    if (lp.mFullSpan) {
      updateAllRemainingSpans(mLayoutState.mLayoutDirection,targetLine);
    }
 else {
      updateRemainingSpans(currentSpan,mLayoutState.mLayoutDirection,targetLine);
    }
    recycle(recycler,mLayoutState);
    if (mLayoutState.mStopInFocusable && view.hasFocusable()) {
      if (lp.mFullSpan) {
        mRemainingSpans.clear();
      }
 else {
        mRemainingSpans.set(currentSpan.mIndex,false);
      }
    }
    added=true;
  }
  if (!added) {
    recycle(recycler,mLayoutState);
  }
  final int diff;
  if (mLayoutState.mLayoutDirection == LayoutState.LAYOUT_START) {
    final int minStart=getMinStart(mPrimaryOrientation.getStartAfterPadding());
    diff=mPrimaryOrientation.getStartAfterPadding() - minStart;
  }
 else {
    final int maxEnd=getMaxEnd(mPrimaryOrientation.getEndAfterPadding());
    diff=maxEnd - mPrimaryOrientation.getEndAfterPadding();
  }
  return diff > 0 ? Math.min(layoutState.mAvailable,diff) : 0;
}
