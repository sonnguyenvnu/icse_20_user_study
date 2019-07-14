@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1) private void applyCompoundDrawableTint(){
  if (!(mDrawablesTintInfo.mHasTint || mDrawablesTintInfo.mHasTintMode)) {
    return;
  }
  if (mIsDrawablesRelative) {
    Drawable[] drawables=getCompoundDrawablesRelative();
    setCompoundDrawablesRelative(drawables[0],drawables[1],drawables[2],drawables[3]);
  }
 else {
    Drawable[] drawables=getCompoundDrawables();
    setCompoundDrawables(drawables[0],drawables[1],drawables[2],drawables[3]);
  }
}
