private static SNode clone(SNode node,Map<SNode,SNode> mapping,boolean copyAttributes){
  if (node == null)   return null;
  jetbrains.mps.smodel.SNode result=new jetbrains.mps.smodel.SNode(node.getConcept());
  mapping.put(node,result);
  copyProperties(node,result);
  copyUserObjects(node,result);
  for (  SNode child : node.getChildren()) {
    if (!copyAttributes && AttributeOperations.isAttribute(child))     continue;
    SContainmentLink role=child.getContainmentLink();
    assert role != null;
    result.addChild(role,clone(child,mapping,copyAttributes));
  }
  return result;
}
