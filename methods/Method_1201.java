/** 
 * Updates the overlay-color rounding of the parent's child drawable. <ul> <li>If rounding mode is OVERLAY_COLOR and the child is not a RoundedCornersDrawable, a new RoundedCornersDrawable is created and the child gets wrapped with it. <li>If rounding mode is OVERLAY_COLOR and the child is already wrapped with a RoundedCornersDrawable, its rounding parameters are updated. <li>If rounding mode is not OVERLAY_COLOR and the child is wrapped with a RoundedCornersDrawable, the rounded drawable gets removed and its child gets attached directly to the parent. </ul>
 */
static void updateOverlayColorRounding(DrawableParent parent,@Nullable RoundingParams roundingParams){
  Drawable child=parent.getDrawable();
  if (roundingParams != null && roundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.OVERLAY_COLOR) {
    if (child instanceof RoundedCornersDrawable) {
      RoundedCornersDrawable roundedCornersDrawable=(RoundedCornersDrawable)child;
      applyRoundingParams(roundedCornersDrawable,roundingParams);
      roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
    }
 else {
      child=parent.setDrawable(sEmptyDrawable);
      child=maybeWrapWithRoundedOverlayColor(child,roundingParams);
      parent.setDrawable(child);
    }
  }
 else   if (child instanceof RoundedCornersDrawable) {
    RoundedCornersDrawable roundedCornersDrawable=(RoundedCornersDrawable)child;
    child=roundedCornersDrawable.setCurrent(sEmptyDrawable);
    parent.setDrawable(child);
    sEmptyDrawable.setCallback(null);
  }
}
