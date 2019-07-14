void ensureBottomGlow(){
  if (mBottomGlow != null) {
    return;
  }
  mBottomGlow=mEdgeEffectFactory.createEdgeEffect(this,EdgeEffectFactory.DIRECTION_BOTTOM);
  if (mClipToPadding) {
    mBottomGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
  }
 else {
    mBottomGlow.setSize(getMeasuredWidth(),getMeasuredHeight());
  }
  applyEdgeEffectColor(mBottomGlow);
}
