@Override protected void getVisibleVirtualViews(List<Integer> virtualViewIds){
  final MountItem mountItem=getAccessibleMountItem(mView);
  if (mountItem == null) {
    return;
  }
  final Component component=mountItem.getComponent();
  final int extraAccessibilityNodesCount=component.getExtraAccessibilityNodesCount();
  for (int i=0; i < extraAccessibilityNodesCount; i++) {
    virtualViewIds.add(i);
  }
}
