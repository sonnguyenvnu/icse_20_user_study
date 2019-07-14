/** 
 * Sets a new failure drawable with scale type.
 * @param resourceId an identifier of an Android drawable or color resource.
 * @param ScalingUtils.ScaleType a new scale type.
 */
public void setFailureImage(int resourceId,ScalingUtils.ScaleType scaleType){
  setFailureImage(mResources.getDrawable(resourceId),scaleType);
}
