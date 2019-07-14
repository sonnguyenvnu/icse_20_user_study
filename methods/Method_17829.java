@Override protected void onPopulateNodeForVirtualView(int virtualViewId,AccessibilityNodeInfoCompat node){
  final MountItem mountItem=getAccessibleMountItem(mView);
  if (mountItem == null) {
    Log.e(TAG,"No accessible mount item found for view: " + mView);
    node.setContentDescription("");
    node.setBoundsInParent(getDefaultBounds());
    return;
  }
  final Drawable drawable=(Drawable)mountItem.getContent();
  final Rect bounds=drawable.getBounds();
  final Component component=mountItem.getComponent();
  final ComponentLifecycle lifecycle=component;
  node.setClassName(lifecycle.getClass().getName());
  if (virtualViewId >= lifecycle.getExtraAccessibilityNodesCount()) {
    Log.e(TAG,"Received unrecognized virtual view id: " + virtualViewId);
    node.setContentDescription("");
    node.setBoundsInParent(getDefaultBounds());
    return;
  }
  lifecycle.onPopulateExtraAccessibilityNode(node,virtualViewId,bounds.left,bounds.top);
}
