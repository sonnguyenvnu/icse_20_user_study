/** 
 * Finds extra accessibility nodes under the given event coordinates. Returns  {@link #INVALID_ID} otherwise.
 */
@Override protected int getVirtualViewAt(float x,float y){
  final MountItem mountItem=getAccessibleMountItem(mView);
  if (mountItem == null) {
    return INVALID_ID;
  }
  final Component component=mountItem.getComponent();
  final ComponentLifecycle lifecycle=component;
  if (lifecycle.getExtraAccessibilityNodesCount() == 0) {
    return INVALID_ID;
  }
  final Drawable drawable=(Drawable)mountItem.getContent();
  final Rect bounds=drawable.getBounds();
  final int virtualViewId=lifecycle.getExtraAccessibilityNodeAt((int)x - bounds.left,(int)y - bounds.top);
  return (virtualViewId >= 0 ? virtualViewId : INVALID_ID);
}
