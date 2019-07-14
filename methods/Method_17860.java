void maybeRegisterTouchExpansion(int index,MountItem mountItem){
  final ViewNodeInfo viewNodeInfo=mountItem.getViewNodeInfo();
  if (viewNodeInfo == null) {
    return;
  }
  final Rect expandedTouchBounds=viewNodeInfo.getExpandedTouchBounds();
  if (expandedTouchBounds == null) {
    return;
  }
  if (this.equals(mountItem.getContent())) {
    return;
  }
  if (mTouchExpansionDelegate == null) {
    mTouchExpansionDelegate=new TouchExpansionDelegate(this);
    setTouchDelegate(mTouchExpansionDelegate);
  }
  mTouchExpansionDelegate.registerTouchExpansion(index,(View)mountItem.getContent(),expandedTouchBounds);
}
