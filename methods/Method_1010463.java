@Override protected SNode instantiate(@Nullable SNode parent) throws IOException {
  SConcept concept=myReadHelper.readConcept(myIn.readShort());
  SNodeId nodeId=myIn.readNodeId();
  SContainmentLink link=myReadHelper.readAggregation(myIn.readShort());
  boolean interfaceNode=false;
  if (myReadHelper.isRequestedInterfaceOnly()) {
    interfaceNode=myReadHelper.isInterface(concept) || link == null;
  }
  jetbrains.mps.smodel.SNode node=interfaceNode ? new InterfaceSNode(concept,nodeId) : new jetbrains.mps.smodel.SNode(concept,nodeId);
  if (parent == null) {
    return node;
  }
  if (!(parent instanceof InterfaceSNode) || node instanceof InterfaceSNode) {
    parent.addChild(link,node);
  }
 else {
    ((InterfaceSNode)parent).skipRole(link);
    hasSkippedNodes=true;
  }
  return node;
}
