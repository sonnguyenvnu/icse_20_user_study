/** 
 * Updates the leaf rounding of the parent's child drawable. <ul> <li>If rounding mode is BITMAP_ONLY and the child is not a rounded drawable, it gets rounded with a new rounded drawable. <li>If rounding mode is BITMAP_ONLY and the child is already rounded, its rounding parameters are updated. <li>If rounding mode is not BITMAP_ONLY and the child is rounded, its rounding parameters are reset so that no rounding occurs. </ul>
 */
static void updateLeafRounding(DrawableParent parent,@Nullable RoundingParams roundingParams,Resources resources){
  parent=findDrawableParentForLeaf(parent);
  Drawable child=parent.getDrawable();
  if (roundingParams != null && roundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.BITMAP_ONLY) {
    if (child instanceof Rounded) {
      Rounded rounded=(Rounded)child;
      applyRoundingParams(rounded,roundingParams);
    }
 else     if (child != null) {
      parent.setDrawable(sEmptyDrawable);
      Drawable rounded=applyLeafRounding(child,roundingParams,resources);
      parent.setDrawable(rounded);
    }
  }
 else   if (child instanceof Rounded) {
    resetRoundingParams((Rounded)child);
  }
}
