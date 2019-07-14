@OnPopulateExtraAccessibilityNode static void onPopulateExtraAccessibilityNode(AccessibilityNodeInfoCompat node,int extraNodeIndex,int componentBoundsLeft,int componentBoundsTop,@Prop(resType=ResType.STRING) CharSequence text,@FromBoundsDefined Layout textLayout,@FromBoundsDefined ClickableSpan[] clickableSpans){
  final Spanned spanned=(Spanned)text;
  final ClickableSpan span=clickableSpans[extraNodeIndex];
  final int start=spanned.getSpanStart(span);
  final int end=spanned.getSpanEnd(span);
  final int startLine=textLayout.getLineForOffset(start);
  final int endLine=textLayout.getLineForOffset(end);
  final int selectionPathEnd=startLine == endLine ? end : textLayout.getLineVisibleEnd(startLine);
  textLayout.getSelectionPath(start,selectionPathEnd,sTempPath);
  sTempPath.computeBounds(sTempRectF,true);
  sTempRect.set(componentBoundsLeft + (int)sTempRectF.left,componentBoundsTop + (int)sTempRectF.top,componentBoundsLeft + (int)sTempRectF.right,componentBoundsTop + (int)sTempRectF.bottom);
  if (sTempRect.isEmpty()) {
    sTempRect.set(0,0,1,1);
    node.setBoundsInParent(sTempRect);
    node.setContentDescription("");
    return;
  }
  node.setBoundsInParent(sTempRect);
  node.setClickable(true);
  node.setFocusable(true);
  node.setEnabled(true);
  node.setVisibleToUser(true);
  if (span instanceof AccessibleClickableSpan) {
    AccessibleClickableSpan accessibleClickableSpan=(AccessibleClickableSpan)span;
    node.setText(accessibleClickableSpan.getAccessibilityDescription());
    if (accessibleClickableSpan.getAccessibilityRole() != null) {
      node.setClassName(accessibleClickableSpan.getAccessibilityRole());
    }
 else {
      node.setClassName(AccessibilityRole.BUTTON);
    }
  }
 else {
    node.setText(spanned.subSequence(start,end));
    node.setClassName(AccessibilityRole.BUTTON);
  }
}
