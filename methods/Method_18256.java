/** 
 * Acquires a  {@link VisibilityOutput} object and computes the bounds for it using the informationstored in the  {@link InternalNode}.
 */
private static VisibilityOutput createVisibilityOutput(InternalNode node,LayoutState layoutState){
  final int l=layoutState.mCurrentX + node.getX();
  final int t=layoutState.mCurrentY + node.getY();
  final int r=l + node.getWidth();
  final int b=t + node.getHeight();
  final EventHandler<VisibleEvent> visibleHandler=node.getVisibleHandler();
  final EventHandler<FocusedVisibleEvent> focusedHandler=node.getFocusedHandler();
  final EventHandler<UnfocusedVisibleEvent> unfocusedHandler=node.getUnfocusedHandler();
  final EventHandler<FullImpressionVisibleEvent> fullImpressionHandler=node.getFullImpressionHandler();
  final EventHandler<InvisibleEvent> invisibleHandler=node.getInvisibleHandler();
  final EventHandler<VisibilityChangedEvent> visibleRectChangedEventHandler=node.getVisibilityChangedHandler();
  final VisibilityOutput visibilityOutput=new VisibilityOutput();
  visibilityOutput.setComponent(node.getTailComponent());
  visibilityOutput.setBounds(l,t,r,b);
  visibilityOutput.setVisibleHeightRatio(node.getVisibleHeightRatio());
  visibilityOutput.setVisibleWidthRatio(node.getVisibleWidthRatio());
  visibilityOutput.setVisibleEventHandler(visibleHandler);
  visibilityOutput.setFocusedEventHandler(focusedHandler);
  visibilityOutput.setUnfocusedEventHandler(unfocusedHandler);
  visibilityOutput.setFullImpressionEventHandler(fullImpressionHandler);
  visibilityOutput.setInvisibleEventHandler(invisibleHandler);
  visibilityOutput.setVisibilityChangedEventHandler(visibleRectChangedEventHandler);
  return visibilityOutput;
}
