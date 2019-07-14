private void maybeMoveTouchExpansionIndexes(MountItem item,int oldIndex,int newIndex){
  final ViewNodeInfo viewNodeInfo=item.getViewNodeInfo();
  if (viewNodeInfo == null) {
    return;
  }
  final Rect expandedTouchBounds=viewNodeInfo.getExpandedTouchBounds();
  if (expandedTouchBounds == null || mTouchExpansionDelegate == null) {
    return;
  }
  mTouchExpansionDelegate.moveTouchExpansionIndexes(oldIndex,newIndex);
}
