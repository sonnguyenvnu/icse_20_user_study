/** 
 * Worst case, we have different widths _and_ colors specified 
 */
private void drawIndividualBorders(Canvas canvas){
  Rect bounds=getBounds();
  if (mState.mBorderLeftWidth > 0 && mState.mBorderLeftColor != QUICK_REJECT_COLOR) {
    drawBorder(canvas,mState.mBorderLeftColor,mState.mBorderLeftWidth,bounds.left,bounds.top,Math.min(bounds.left + mState.mBorderLeftWidth,bounds.right),bounds.bottom,true);
  }
  if (mState.mBorderRightWidth > 0 && mState.mBorderRightColor != QUICK_REJECT_COLOR) {
    drawBorder(canvas,mState.mBorderRightColor,mState.mBorderRightWidth,Math.max(bounds.right - mState.mBorderRightWidth,bounds.left),bounds.top,bounds.right,bounds.bottom,true);
  }
  if (mState.mBorderTopWidth > 0 && mState.mBorderTopColor != QUICK_REJECT_COLOR) {
    drawBorder(canvas,mState.mBorderTopColor,mState.mBorderTopWidth,bounds.left,bounds.top,bounds.right,Math.min(bounds.top + mState.mBorderTopWidth,bounds.bottom),false);
  }
  if (mState.mBorderBottomWidth > 0 && mState.mBorderBottomColor != QUICK_REJECT_COLOR) {
    drawBorder(canvas,mState.mBorderBottomColor,mState.mBorderBottomWidth,bounds.left,Math.max(bounds.bottom - mState.mBorderBottomWidth,bounds.top),bounds.right,bounds.bottom,false);
  }
}
