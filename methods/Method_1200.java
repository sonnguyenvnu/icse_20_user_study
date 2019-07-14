/** 
 * Wraps the given drawable with a new  {@link ScaleTypeDrawable}. <p>If the provided drawable or scale type is null, the given drawable is returned without being wrapped.
 * @return the wrapping scale type drawable, or the original drawable if the wrapping didn't takeplace
 */
@Nullable static Drawable maybeWrapWithScaleType(@Nullable Drawable drawable,@Nullable ScalingUtils.ScaleType scaleType,@Nullable PointF focusPoint){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("WrappingUtils#maybeWrapWithScaleType");
  }
  if (drawable == null || scaleType == null) {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
    return drawable;
  }
  ScaleTypeDrawable scaleTypeDrawable=new ScaleTypeDrawable(drawable,scaleType);
  if (focusPoint != null) {
    scaleTypeDrawable.setFocusPoint(focusPoint);
  }
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
  return scaleTypeDrawable;
}
