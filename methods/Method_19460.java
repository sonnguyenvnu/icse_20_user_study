@OnPopulateAccessibilityNode static void onPopulateAccessibilityNode(View host,AccessibilityNodeInfoCompat node,@Prop(resType=ResType.STRING) CharSequence text,@Prop(optional=true,resType=ResType.BOOL) boolean isSingleLine){
  if (ViewCompat.getImportantForAccessibility(host) == ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_AUTO) {
    ViewCompat.setImportantForAccessibility(host,ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES);
  }
  CharSequence contentDescription=node.getContentDescription();
  node.setText(contentDescription != null ? contentDescription : text);
  node.setContentDescription(contentDescription != null ? contentDescription : text);
  node.addAction(AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
  node.addAction(AccessibilityNodeInfoCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
  node.setMovementGranularities(AccessibilityNodeInfoCompat.MOVEMENT_GRANULARITY_CHARACTER | AccessibilityNodeInfoCompat.MOVEMENT_GRANULARITY_WORD | AccessibilityNodeInfoCompat.MOVEMENT_GRANULARITY_PARAGRAPH);
  if (!isSingleLine) {
    node.setMultiLine(true);
  }
}
