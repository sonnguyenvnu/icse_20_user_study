public void put(SNode node){
  if (myDisabled)   return;
  if (node.getNodeId() == null || node.getModel() == null) {
    myNodesWithoutRefs.add(node);
    return;
  }
  add(node.getModel().getReference(),node.getNodeId(),node);
}
