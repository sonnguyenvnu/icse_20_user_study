public static void setupHierarchy(@Prop(optional=true) ScalingUtils.ScaleType actualImageScaleType,@Prop(optional=true) int fadeDuration,@Prop(optional=true,resType=DRAWABLE) Drawable failureImage,@Prop(optional=true) ScalingUtils.ScaleType failureImageScaleType,@Prop(optional=true,resType=DRAWABLE) Drawable placeholderImage,@Prop(optional=true) PointF placeholderImageFocusPoint,@Prop(optional=true) ScalingUtils.ScaleType placeholderImageScaleType,@Prop(optional=true,resType=DRAWABLE) Drawable progressBarImage,@Prop(optional=true) ScalingUtils.ScaleType progressBarImageScaleType,@Prop(optional=true,resType=DRAWABLE) Drawable retryImage,@Prop(optional=true) ScalingUtils.ScaleType retryImageScaleType,@Prop(optional=true) RoundingParams roundingParams,@Prop(optional=true) ColorFilter colorFilter,GenericDraweeHierarchy draweeHierarchy){
  if (placeholderImage == null) {
    draweeHierarchy.setPlaceholderImage(null);
  }
 else {
    draweeHierarchy.setPlaceholderImage(placeholderImage,placeholderImageScaleType);
  }
  if (placeholderImageScaleType == ScalingUtils.ScaleType.FOCUS_CROP) {
    draweeHierarchy.setPlaceholderImageFocusPoint(placeholderImageFocusPoint);
  }
  draweeHierarchy.setActualImageScaleType(actualImageScaleType);
  draweeHierarchy.setFadeDuration(fadeDuration);
  if (failureImage == null) {
    draweeHierarchy.setFailureImage(null);
  }
 else {
    draweeHierarchy.setFailureImage(failureImage,failureImageScaleType);
  }
  if (progressBarImage == null) {
    draweeHierarchy.setProgressBarImage(null);
  }
 else {
    draweeHierarchy.setProgressBarImage(progressBarImage,progressBarImageScaleType);
  }
  if (retryImage == null) {
    draweeHierarchy.setRetryImage(null);
  }
 else {
    draweeHierarchy.setRetryImage(retryImage,retryImageScaleType);
  }
  draweeHierarchy.setRoundingParams(roundingParams);
  draweeHierarchy.setActualImageColorFilter(colorFilter);
}
