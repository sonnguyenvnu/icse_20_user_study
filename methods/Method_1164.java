/** 
 * Gets the lowest parent drawable for the layer at the specified index. Following drawables are considered as parents: FadeDrawable, MatrixDrawable, ScaleTypeDrawable. This is because those drawables are added automatically by the hierarchy (if specified), whereas their children are created externally by the client code. When we need to change the previously set drawable this is the parent whose child needs to be replaced.
 */
private DrawableParent getParentDrawableAtIndex(int index){
  DrawableParent parent=mFadeDrawable.getDrawableParentForIndex(index);
  if (parent.getDrawable() instanceof MatrixDrawable) {
    parent=(MatrixDrawable)parent.getDrawable();
  }
  if (parent.getDrawable() instanceof ScaleTypeDrawable) {
    parent=(ScaleTypeDrawable)parent.getDrawable();
  }
  return parent;
}
