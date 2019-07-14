@Nullable private Drawable buildActualImageBranch(Drawable drawable,@Nullable ScalingUtils.ScaleType scaleType,@Nullable PointF focusPoint,@Nullable ColorFilter colorFilter){
  drawable.setColorFilter(colorFilter);
  drawable=WrappingUtils.maybeWrapWithScaleType(drawable,scaleType,focusPoint);
  return drawable;
}
