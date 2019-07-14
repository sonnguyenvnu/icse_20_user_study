public boolean getFocusable(){
  final NodeInfo nodeInfo=mNode.getNodeInfo();
  if (nodeInfo != null) {
    return nodeInfo.getFocusState() == NodeInfo.FOCUS_SET_TRUE;
  }
  return false;
}
