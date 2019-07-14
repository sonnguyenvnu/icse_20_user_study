private void prepareDebugTextParameters(Rect bounds){
  int textSizePx=Math.min(bounds.width() / mMaxLineLength,bounds.height() / mDebugData.size());
  textSizePx=Math.min(MAX_TEXT_SIZE_PX,Math.max(MIN_TEXT_SIZE_PX,textSizePx));
  mPaint.setTextSize(textSizePx);
  mLineIncrementPx=textSizePx + TEXT_LINE_SPACING_PX;
  if (mTextGravity == Gravity.BOTTOM) {
    mLineIncrementPx*=-1;
  }
  mStartTextXPx=bounds.left + TEXT_PADDING_PX;
  mStartTextYPx=mTextGravity == Gravity.BOTTOM ? bounds.bottom - TEXT_PADDING_PX : bounds.top + TEXT_PADDING_PX + MIN_TEXT_SIZE_PX;
}
