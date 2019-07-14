/** 
 * Applies scale type and rounding (both if specified). 
 */
@Nullable private Drawable buildBranch(@Nullable Drawable drawable,@Nullable ScalingUtils.ScaleType scaleType){
  drawable=WrappingUtils.maybeApplyLeafRounding(drawable,mRoundingParams,mResources);
  drawable=WrappingUtils.maybeWrapWithScaleType(drawable,scaleType);
  return drawable;
}
