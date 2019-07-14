void setExpandedTouchBounds(InternalNode node,int l,int t,int r,int b){
  if (!node.hasTouchExpansion()) {
    return;
  }
  final int touchExpansionLeft=node.getTouchExpansionLeft();
  final int touchExpansionTop=node.getTouchExpansionTop();
  final int touchExpansionRight=node.getTouchExpansionRight();
  final int touchExpansionBottom=node.getTouchExpansionBottom();
  if (touchExpansionLeft == 0 && touchExpansionTop == 0 && touchExpansionRight == 0 && touchExpansionBottom == 0) {
    return;
  }
  if (mExpandedTouchBounds != null) {
    throw new IllegalStateException("ExpandedTouchBounds already initialized for this " + "ViewNodeInfo.");
  }
  mExpandedTouchBounds=new Rect();
  mExpandedTouchBounds.set(l - touchExpansionLeft,t - touchExpansionTop,r + touchExpansionRight,b + touchExpansionBottom);
}
