@GetExtraAccessibilityNodesCount static int getExtraAccessibilityNodesCount(@Prop(optional=true,resType=ResType.BOOL) boolean accessibleClickableSpans,@FromBoundsDefined ClickableSpan[] clickableSpans){
  return (accessibleClickableSpans && clickableSpans != null) ? clickableSpans.length : 0;
}
