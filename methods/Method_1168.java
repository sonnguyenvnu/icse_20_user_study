/** 
 * Sets a new placeholder drawable with scale type. 
 */
public void setPlaceholderImage(Drawable drawable,ScalingUtils.ScaleType scaleType){
  setChildDrawableAtIndex(PLACEHOLDER_IMAGE_INDEX,drawable);
  getScaleTypeDrawableAtIndex(PLACEHOLDER_IMAGE_INDEX).setScaleType(scaleType);
}
