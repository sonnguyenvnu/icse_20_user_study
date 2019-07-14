/** 
 * Determine if a given  {@link InternalNode} within the context of a given {@link LayoutState}requires to be wrapped inside a view.
 * @see #needsHostView(InternalNode,LayoutState)
 */
private static boolean hasViewContent(InternalNode node,LayoutState layoutState){
  final Component component=node.getTailComponent();
  final NodeInfo nodeInfo=node.getNodeInfo();
  final boolean implementsAccessibility=(nodeInfo != null && nodeInfo.needsAccessibilityDelegate()) || (component != null && component.implementsAccessibility());
  final int importantForAccessibility=node.getImportantForAccessibility();
  final boolean hasAccessibilityContent=layoutState.mAccessibilityEnabled && importantForAccessibility != IMPORTANT_FOR_ACCESSIBILITY_NO && (implementsAccessibility || (nodeInfo != null && !TextUtils.isEmpty(nodeInfo.getContentDescription())) || importantForAccessibility != IMPORTANT_FOR_ACCESSIBILITY_AUTO);
  final boolean hasFocusChangeHandler=(nodeInfo != null && nodeInfo.hasFocusChangeHandler());
  final boolean hasEnabledTouchEventHandlers=nodeInfo != null && nodeInfo.hasTouchEventHandlers() && nodeInfo.getEnabledState() != ENABLED_SET_FALSE;
  final boolean hasViewTag=(nodeInfo != null && nodeInfo.getViewTag() != null);
  final boolean hasViewTags=(nodeInfo != null && nodeInfo.getViewTags() != null);
  final boolean hasShadowElevation=(nodeInfo != null && nodeInfo.getShadowElevation() != 0);
  final boolean hasOutlineProvider=(nodeInfo != null && nodeInfo.getOutlineProvider() != null);
  final boolean hasClipToOutline=(nodeInfo != null && nodeInfo.getClipToOutline());
  final boolean isFocusableSetTrue=(nodeInfo != null && nodeInfo.getFocusState() == FOCUS_SET_TRUE);
  final boolean isClickableSetTrue=(nodeInfo != null && nodeInfo.getClickableState() == CLICKABLE_SET_TRUE);
  final boolean hasClipChildrenSet=(nodeInfo != null && nodeInfo.isClipChildrenSet());
  return hasFocusChangeHandler || hasEnabledTouchEventHandlers || hasViewTag || hasViewTags || hasShadowElevation || hasOutlineProvider || hasClipToOutline || hasClipChildrenSet || hasAccessibilityContent || isFocusableSetTrue || isClickableSetTrue;
}
