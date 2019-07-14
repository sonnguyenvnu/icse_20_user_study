/** 
 * Applies rounding on the drawable's leaf. <p> Currently only  {@link BitmapDrawable} or {@link ColorDrawable} leafs can be rounded.<p> If the leaf cannot be rounded, or the rounding params do not specify BITMAP_ONLY mode, the given drawable is returned without being rounded. <p> If the given drawable is a leaf itself, and it can be rounded, then the rounded drawable is returned. <p> If the given drawable is not a leaf, and its leaf can be rounded, the leaf gets rounded, and the original drawable is returned.
 * @return the rounded drawable, or the original drawable if the rounding didn't take placeor it took place on a drawable's child
 */
static Drawable maybeApplyLeafRounding(@Nullable Drawable drawable,@Nullable RoundingParams roundingParams,Resources resources){
  try {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.beginSection("WrappingUtils#maybeApplyLeafRounding");
    }
    if (drawable == null || roundingParams == null || roundingParams.getRoundingMethod() != RoundingParams.RoundingMethod.BITMAP_ONLY) {
      return drawable;
    }
    if (drawable instanceof ForwardingDrawable) {
      DrawableParent parent=findDrawableParentForLeaf((ForwardingDrawable)drawable);
      Drawable child=parent.setDrawable(sEmptyDrawable);
      child=applyLeafRounding(child,roundingParams,resources);
      parent.setDrawable(child);
      return drawable;
    }
 else {
      return applyLeafRounding(drawable,roundingParams,resources);
    }
  }
  finally {
    if (FrescoSystrace.isTracing()) {
      FrescoSystrace.endSection();
    }
  }
}
