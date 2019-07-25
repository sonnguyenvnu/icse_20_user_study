private void update(InterfaceSNode node){
  if (node.hasSkippedChildren()) {
    SNode fullNode=myFullModel.getNode(node.getNodeId());
    if (fullNode == null) {
      final String m="model %s: no peer node in full model for %s (in %s)";
      LOG.error(String.format(m,myModel.getModelName(),node.getNodeId(),myDataOwner.getSource().getLocation()));
      return;
    }
    Iterator<? extends SNode> it=fullNode.getChildren().iterator();
    SNode curr=it.hasNext() ? it.next() : null;
    for (    SNode child : node.getChildren()) {
      SNodeId childId=child.getNodeId();
      while (curr != null && !childId.equals(curr.getNodeId())) {
        SNode next=it.hasNext() ? it.next() : null;
        SContainmentLink role=curr.getContainmentLink();
        curr.delete();
        node.insertChildBefore(role,curr,child);
        curr=next;
      }
      if (curr != null && childId.equals(curr.getNodeId())) {
        curr=it.hasNext() ? it.next() : null;
      }
      if (curr == null) {
        break;
      }
    }
    while (curr != null) {
      SNode next=it.hasNext() ? it.next() : null;
      SContainmentLink role=curr.getContainmentLink();
      curr.delete();
      node.addChild(role,curr);
      curr=next;
    }
    node.cleanSkippedRoles();
  }
  for (  SNode n : node.getChildren()) {
    if (n instanceof InterfaceSNode) {
      update((InterfaceSNode)n);
    }
  }
}
