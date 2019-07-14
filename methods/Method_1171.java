/** 
 * Sets a new failure drawable with scale type. 
 */
public void setFailureImage(Drawable drawable,ScalingUtils.ScaleType scaleType){
  setChildDrawableAtIndex(FAILURE_IMAGE_INDEX,drawable);
  getScaleTypeDrawableAtIndex(FAILURE_IMAGE_INDEX).setScaleType(scaleType);
}
