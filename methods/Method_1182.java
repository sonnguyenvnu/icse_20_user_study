/** 
 * Sets the retry image and its scale type.
 * @param resourceId an identifier of an Android drawable or color resource
 * @param retryImageScaleType scale type for the retry image
 * @return modified instance of this builder
 */
public GenericDraweeHierarchyBuilder setRetryImage(int resourceId,@Nullable ScalingUtils.ScaleType retryImageScaleType){
  mRetryImage=mResources.getDrawable(resourceId);
  mRetryImageScaleType=retryImageScaleType;
  return this;
}
