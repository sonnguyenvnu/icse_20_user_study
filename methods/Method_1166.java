/** 
 * Returns whether the given layer has a scale type drawable.
 */
private boolean hasScaleTypeDrawableAtIndex(int index){
  DrawableParent parent=getParentDrawableAtIndex(index);
  return (parent instanceof ScaleTypeDrawable);
}
