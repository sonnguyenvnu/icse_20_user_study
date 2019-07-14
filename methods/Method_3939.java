/** 
 * Does not perform bounds checking. Used by internal methods that have already validated input. <p> It also reports any unused scroll request to the related EdgeEffect.
 * @param x The amount of horizontal scroll request
 * @param y The amount of vertical scroll request
 * @param ev The originating MotionEvent, or null if not from a touch event.
 * @return Whether any scroll was consumed in either direction.
 */
boolean scrollByInternal(int x,int y,MotionEvent ev){
  int unconsumedX=0;
  int unconsumedY=0;
  int consumedX=0;
  int consumedY=0;
  consumePendingUpdateOperations();
  if (mAdapter != null) {
    mReusableIntPair[0]=0;
    mReusableIntPair[1]=0;
    scrollStep(x,y,mReusableIntPair);
    consumedX=mReusableIntPair[0];
    consumedY=mReusableIntPair[1];
    unconsumedX=x - consumedX;
    unconsumedY=y - consumedY;
  }
  if (!mItemDecorations.isEmpty()) {
    invalidate();
  }
  mReusableIntPair[0]=0;
  mReusableIntPair[1]=0;
  dispatchNestedScroll(consumedX,consumedY,unconsumedX,unconsumedY,mScrollOffset,TYPE_TOUCH,mReusableIntPair);
  unconsumedX-=mReusableIntPair[0];
  unconsumedY-=mReusableIntPair[1];
  mLastTouchX-=mScrollOffset[0];
  mLastTouchY-=mScrollOffset[1];
  if (ev != null) {
    ev.offsetLocation(mScrollOffset[0],mScrollOffset[1]);
  }
  mNestedOffsets[0]+=mScrollOffset[0];
  mNestedOffsets[1]+=mScrollOffset[1];
  if (getOverScrollMode() != View.OVER_SCROLL_NEVER) {
    if (ev != null && !MotionEventCompat.isFromSource(ev,InputDevice.SOURCE_MOUSE)) {
      pullGlows(ev.getX(),unconsumedX,ev.getY(),unconsumedY);
    }
    considerReleasingGlowsOnScroll(x,y);
  }
  if (consumedX != 0 || consumedY != 0) {
    dispatchOnScrolled(consumedX,consumedY);
  }
  if (!awakenScrollBars()) {
    invalidate();
  }
  return consumedX != 0 || consumedY != 0;
}
