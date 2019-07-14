/** 
 * Sets the rounding params. 
 */
public void setRoundingParams(@Nullable RoundingParams roundingParams){
  mRoundingParams=roundingParams;
  WrappingUtils.updateOverlayColorRounding(mTopLevelDrawable,mRoundingParams);
  for (int i=0; i < mFadeDrawable.getNumberOfLayers(); i++) {
    WrappingUtils.updateLeafRounding(getParentDrawableAtIndex(i),mRoundingParams,mResources);
  }
}
