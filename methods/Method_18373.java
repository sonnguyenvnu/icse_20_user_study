private static boolean shouldUpdateViewInfo(LayoutOutput layoutOutput,MountItem currentMountItem){
  final ViewNodeInfo nextViewNodeInfo=layoutOutput.getViewNodeInfo();
  final ViewNodeInfo currentViewNodeInfo=currentMountItem.getViewNodeInfo();
  if ((currentViewNodeInfo == null && nextViewNodeInfo != null) || (currentViewNodeInfo != null && !currentViewNodeInfo.isEquivalentTo(nextViewNodeInfo))) {
    return true;
  }
  final NodeInfo nextNodeInfo=layoutOutput.getNodeInfo();
  final NodeInfo currentNodeInfo=currentMountItem.getNodeInfo();
  return (currentNodeInfo == null && nextNodeInfo != null) || (currentNodeInfo != null && !currentNodeInfo.isEquivalentTo(nextNodeInfo));
}
