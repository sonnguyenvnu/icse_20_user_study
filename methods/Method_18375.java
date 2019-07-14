private static void updateBoundsForMountedLayoutOutput(LayoutOutput layoutOutput,LayoutState layoutState,MountItem item){
  if (layoutOutput.getId() == ROOT_HOST_ID) {
    return;
  }
  getActualBounds(layoutOutput,layoutState,sTempRect);
  final boolean forceTraversal=Component.isMountViewSpec(layoutOutput.getComponent()) && ((View)item.getContent()).isLayoutRequested();
  applyBoundsToMountContent(item.getContent(),sTempRect.left,sTempRect.top,sTempRect.right,sTempRect.bottom,forceTraversal);
}
