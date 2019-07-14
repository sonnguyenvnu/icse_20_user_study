private static void unsetViewAttributes(MountItem item,boolean isHostView){
  final View view=(View)item.getContent();
  final NodeInfo nodeInfo=item.getNodeInfo();
  if (nodeInfo != null) {
    if (nodeInfo.getClickHandler() != null) {
      unsetClickHandler(view);
    }
    if (nodeInfo.getLongClickHandler() != null) {
      unsetLongClickHandler(view);
    }
    if (nodeInfo.getFocusChangeHandler() != null) {
      unsetFocusChangeHandler(view);
    }
    if (nodeInfo.getTouchHandler() != null) {
      unsetTouchHandler(view);
    }
    if (nodeInfo.getInterceptTouchHandler() != null) {
      unsetInterceptTouchEventHandler(view);
    }
    unsetViewTag(view);
    unsetViewTags(view,nodeInfo.getViewTags());
    unsetShadowElevation(view,nodeInfo.getShadowElevation());
    unsetOutlineProvider(view,nodeInfo.getOutlineProvider());
    unsetClipToOutline(view,nodeInfo.getClipToOutline());
    unsetClipChildren(view,nodeInfo.getClipChildren());
    if (!TextUtils.isEmpty(nodeInfo.getContentDescription())) {
      unsetContentDescription(view);
    }
    unsetScale(view,nodeInfo);
    unsetAlpha(view,nodeInfo);
    unsetRotation(view,nodeInfo);
    unsetRotationX(view,nodeInfo);
    unsetRotationY(view,nodeInfo);
  }
  view.setClickable(item.isViewClickable());
  view.setLongClickable(item.isViewLongClickable());
  unsetFocusable(view,item);
  unsetEnabled(view,item);
  unsetSelected(view,item);
  if (item.getImportantForAccessibility() != IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
    unsetImportantForAccessibility(view);
  }
  unsetAccessibilityDelegate(view);
  final ViewNodeInfo viewNodeInfo=item.getViewNodeInfo();
  if (viewNodeInfo != null) {
    unsetViewStateListAnimator(view,viewNodeInfo);
    if (!isHostView) {
      unsetViewPadding(view,viewNodeInfo);
      unsetViewBackground(view,viewNodeInfo);
      unsetViewForeground(view,viewNodeInfo);
      unsetViewLayoutDirection(view);
    }
  }
}
