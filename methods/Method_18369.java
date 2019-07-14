/** 
 * Returns true if the component is in the focused visible range.
 */
private boolean isInFocusedRange(Rect componentBounds,Rect componentVisibleBounds){
  final View parent=(View)mLithoView.getParent();
  if (parent == null) {
    return false;
  }
  final int halfViewportArea=parent.getWidth() * parent.getHeight() / 2;
  final int totalComponentArea=computeRectArea(componentBounds);
  final int visibleComponentArea=computeRectArea(componentVisibleBounds);
  return (totalComponentArea >= halfViewportArea) ? (visibleComponentArea >= halfViewportArea) : componentBounds.equals(componentVisibleBounds);
}
