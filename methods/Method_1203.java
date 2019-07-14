/** 
 * Wraps the given drawable with a new  {@link RoundedCornersDrawable}. <p> If the provided drawable is null, or if the rounding params do not specify OVERLAY_COLOR mode, the given drawable is returned without being wrapped.
 * @return the wrapping rounded drawable, or the original drawable if the wrapping didn'ttake place
 */
static Drawable maybeWrapWithRoundedOverlayColor(@Nullable Drawable drawable,@Nullable RoundingParams roundingParams){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("WrappingUtils#maybeWrapWithRoundedOverlayColor");
    }
    if (drawable == null || roundingParams == null || roundingParams.getRoundingMethod() != RoundingParams.RoundingMethod.OVERLAY_COLOR) {
      return drawable;
    }
    RoundedCornersDrawable roundedCornersDrawable=new RoundedCornersDrawable(drawable);
    applyRoundingParams(roundedCornersDrawable,roundingParams);
    roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
    return roundedCornersDrawable;
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
