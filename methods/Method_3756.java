/** 
 * Notify the scroller of external change of the scroll, e.g. through dragging or flinging on the view itself.
 * @param offsetX The new scroll X offset.
 * @param offsetY The new scroll Y offset.
 */
void updateScrollPosition(int offsetX,int offsetY){
  int verticalContentLength=mRecyclerView.computeVerticalScrollRange();
  int verticalVisibleLength=mRecyclerViewHeight;
  mNeedVerticalScrollbar=verticalContentLength - verticalVisibleLength > 0 && mRecyclerViewHeight >= mScrollbarMinimumRange;
  int horizontalContentLength=mRecyclerView.computeHorizontalScrollRange();
  int horizontalVisibleLength=mRecyclerViewWidth;
  mNeedHorizontalScrollbar=horizontalContentLength - horizontalVisibleLength > 0 && mRecyclerViewWidth >= mScrollbarMinimumRange;
  if (!mNeedVerticalScrollbar && !mNeedHorizontalScrollbar) {
    if (mState != STATE_HIDDEN) {
      setState(STATE_HIDDEN);
    }
    return;
  }
  if (mNeedVerticalScrollbar) {
    float middleScreenPos=offsetY + verticalVisibleLength / 2.0f;
    mVerticalThumbCenterY=(int)((verticalVisibleLength * middleScreenPos) / verticalContentLength);
    mVerticalThumbHeight=Math.min(verticalVisibleLength,(verticalVisibleLength * verticalVisibleLength) / verticalContentLength);
  }
  if (mNeedHorizontalScrollbar) {
    float middleScreenPos=offsetX + horizontalVisibleLength / 2.0f;
    mHorizontalThumbCenterX=(int)((horizontalVisibleLength * middleScreenPos) / horizontalContentLength);
    mHorizontalThumbWidth=Math.min(horizontalVisibleLength,(horizontalVisibleLength * horizontalVisibleLength) / horizontalContentLength);
  }
  if (mState == STATE_HIDDEN || mState == STATE_VISIBLE) {
    setState(STATE_VISIBLE);
  }
}
