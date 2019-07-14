@Override public void setCompoundDrawableTintList(@Nullable ColorStateList tint){
  if (hasFrameworkDrawableTint()) {
    super.setCompoundDrawableTintList(tint);
    return;
  }
  mDrawablesTintInfo.mTintList=tint;
  mDrawablesTintInfo.mHasTint=true;
  applyCompoundDrawableTint();
}
