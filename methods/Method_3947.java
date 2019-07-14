void ensureLeftGlow(){
  if (mLeftGlow != null) {
    return;
  }
  mLeftGlow=mEdgeEffectFactory.createEdgeEffect(this,EdgeEffectFactory.DIRECTION_LEFT);
  if (mClipToPadding) {
    mLeftGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(),getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
  }
 else {
    mLeftGlow.setSize(getMeasuredHeight(),getMeasuredWidth());
  }
  applyEdgeEffectColor(mLeftGlow);
}
