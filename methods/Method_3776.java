int getSpaceForSpanRange(int startSpan,int spanSize){
  if (mOrientation == VERTICAL && isLayoutRTL()) {
    return mCachedBorders[mSpanCount - startSpan] - mCachedBorders[mSpanCount - startSpan - spanSize];
  }
 else {
    return mCachedBorders[startSpan + spanSize] - mCachedBorders[startSpan];
  }
}
