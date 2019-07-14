void ensureTopGlow(){
  if (mTopGlow != null) {
    return;
  }
  mTopGlow=mEdgeEffectFactory.createEdgeEffect(this,EdgeEffectFactory.DIRECTION_TOP);
  if (mClipToPadding) {
    mTopGlow.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
  }
 else {
    mTopGlow.setSize(getMeasuredWidth(),getMeasuredHeight());
  }
  applyEdgeEffectColor(mTopGlow);
}
