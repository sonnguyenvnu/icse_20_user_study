/** 
 * Sets a new progress bar drawable with scale type. 
 */
public void setProgressBarImage(Drawable drawable,ScalingUtils.ScaleType scaleType){
  setChildDrawableAtIndex(PROGRESS_BAR_IMAGE_INDEX,drawable);
  getScaleTypeDrawableAtIndex(PROGRESS_BAR_IMAGE_INDEX).setScaleType(scaleType);
}
