/** 
 * set up the rect of back and thumb
 */
private void setup(){
  if (mThumbWidth == 0 || mThumbHeight == 0 || mBackWidth == 0 || mBackHeight == 0) {
    return;
  }
  if (mThumbRadius == -1) {
    mThumbRadius=Math.min(mThumbWidth,mThumbHeight) / 2;
  }
  if (mBackRadius == -1) {
    mBackRadius=Math.min(mBackWidth,mBackHeight) / 2;
  }
  int contentWidth=getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
  int contentHeight=getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
  int drawingWidth=ceil(mBackWidth - Math.min(0,mThumbMargin.left) - Math.min(0,mThumbMargin.right));
  int drawingHeight=ceil(mBackHeight - Math.min(0,mThumbMargin.top) - Math.min(0,mThumbMargin.bottom));
  float thumbTop;
  if (contentHeight <= drawingHeight) {
    thumbTop=getPaddingTop() + Math.max(0,mThumbMargin.top);
  }
 else {
    thumbTop=getPaddingTop() + Math.max(0,mThumbMargin.top) + (contentHeight - drawingHeight + 1) / 2;
  }
  float thumbLeft;
  if (contentWidth <= mBackWidth) {
    thumbLeft=getPaddingLeft() + Math.max(0,mThumbMargin.left);
  }
 else {
    thumbLeft=getPaddingLeft() + Math.max(0,mThumbMargin.left) + (contentWidth - drawingWidth + 1) / 2;
  }
  mThumbRectF.set(thumbLeft,thumbTop,thumbLeft + mThumbWidth,thumbTop + mThumbHeight);
  float backLeft=mThumbRectF.left - mThumbMargin.left;
  mBackRectF.set(backLeft,mThumbRectF.top - mThumbMargin.top,backLeft + mBackWidth,mThumbRectF.top - mThumbMargin.top + mBackHeight);
  mSafeRectF.set(mThumbRectF.left,0,mBackRectF.right - mThumbMargin.right - mThumbRectF.width(),0);
  float minBackRadius=Math.min(mBackRectF.width(),mBackRectF.height()) / 2.f;
  mBackRadius=Math.min(minBackRadius,mBackRadius);
  if (mBackDrawable != null) {
    mBackDrawable.setBounds((int)mBackRectF.left,(int)mBackRectF.top,ceil(mBackRectF.right),ceil(mBackRectF.bottom));
  }
  if (mOnLayout != null) {
    float onLeft=mBackRectF.left + (mBackRectF.width() + mTextThumbInset - mThumbWidth - mThumbMargin.right - mOnLayout.getWidth()) / 2f - mTextAdjust;
    float onTop=mBackRectF.top + (mBackRectF.height() - mOnLayout.getHeight()) / 2;
    mTextOnRectF.set(onLeft,onTop,onLeft + mOnLayout.getWidth(),onTop + mOnLayout.getHeight());
  }
  if (mOffLayout != null) {
    float offLeft=mBackRectF.right - (mBackRectF.width() + mTextThumbInset - mThumbWidth - mThumbMargin.left - mOffLayout.getWidth()) / 2f - mOffLayout.getWidth() + mTextAdjust;
    float offTop=mBackRectF.top + (mBackRectF.height() - mOffLayout.getHeight()) / 2;
    mTextOffRectF.set(offLeft,offTop,offLeft + mOffLayout.getWidth(),offTop + mOffLayout.getHeight());
  }
  mReady=true;
}
