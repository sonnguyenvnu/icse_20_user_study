/** 
 * Sets the failure image and its scale type.
 * @param resourceId an identifier of an Android drawable or color resource
 * @param failureImageScaleType scale type for the failure image
 * @return modified instance of this builder
 */
public GenericDraweeHierarchyBuilder setFailureImage(int resourceId,@Nullable ScalingUtils.ScaleType failureImageScaleType){
  mFailureImage=mResources.getDrawable(resourceId);
  mFailureImageScaleType=failureImageScaleType;
  return this;
}
