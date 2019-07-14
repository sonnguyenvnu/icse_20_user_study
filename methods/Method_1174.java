/** 
 * Sets a new retry drawable with scale type. 
 */
public void setRetryImage(Drawable drawable,ScalingUtils.ScaleType scaleType){
  setChildDrawableAtIndex(RETRY_IMAGE_INDEX,drawable);
  getScaleTypeDrawableAtIndex(RETRY_IMAGE_INDEX).setScaleType(scaleType);
}
