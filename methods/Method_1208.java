/** 
 * Finds the immediate parent of a leaf drawable.
 */
static DrawableParent findDrawableParentForLeaf(DrawableParent parent){
  while (true) {
    Drawable child=parent.getDrawable();
    if (child == parent || !(child instanceof DrawableParent)) {
      break;
    }
    parent=(DrawableParent)child;
  }
  return parent;
}
