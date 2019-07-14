void ensureRightGlow(){
  if (mRightGlow != null) {
    return;
  }
  mRightGlow=mEdgeEffectFactory.createEdgeEffect(this,EdgeEffectFactory.DIRECTION_RIGHT);
  if (mClipToPadding) {
    mRightGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(),getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
  }
 else {
    mRightGlow.setSize(getMeasuredHeight(),getMeasuredWidth());
  }
  applyEdgeEffectColor(mRightGlow);
}
