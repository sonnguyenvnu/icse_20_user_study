void maybeUnregisterTouchExpansion(int index,MountItem mountItem){
  final ViewNodeInfo viewNodeInfo=mountItem.getViewNodeInfo();
  if (viewNodeInfo == null) {
    return;
  }
  if (mTouchExpansionDelegate == null || viewNodeInfo.getExpandedTouchBounds() == null) {
    return;
  }
  if (this.equals(mountItem.getContent())) {
    return;
  }
  mTouchExpansionDelegate.unregisterTouchExpansion(index);
}
