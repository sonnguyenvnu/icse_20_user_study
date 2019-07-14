/** 
 * Sets the drawable at the specified index while keeping the old scale type and rounding. In case the given drawable is null, scale type gets cleared too.
 */
private void setChildDrawableAtIndex(int index,@Nullable Drawable drawable){
  if (drawable == null) {
    mFadeDrawable.setDrawable(index,null);
    return;
  }
  drawable=WrappingUtils.maybeApplyLeafRounding(drawable,mRoundingParams,mResources);
  getParentDrawableAtIndex(index).setDrawable(drawable);
}
