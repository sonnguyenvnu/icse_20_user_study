@Nullable public Drawable resolveDrawableRes(@DrawableRes int resId){
  if (resId == 0) {
    return null;
  }
  return mResources.getDrawable(resId);
}
