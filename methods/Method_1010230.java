@Override public SNode resolve(SNode contextNode,@NotNull String refText){
  SNode result=null;
  for (  SModel model : myModels) {
    if (model == null) {
      continue;
    }
    Iterable<SNode> nodes;
    SAbstractConcept conceptToCheck;
    if (myRootsOnly) {
      nodes=model.getRootNodes();
      conceptToCheck=myTargetConcept;
    }
 else     if (myTargetConcept != null) {
      nodes=SModelOperations.getNodes(model,myTargetConcept);
      conceptToCheck=null;
    }
 else {
      nodes=SNodeUtil.getDescendants(model);
      conceptToCheck=null;
    }
    for (    SNode node : nodes) {
      if (conceptToCheck != null && !(node.isInstanceOfConcept(conceptToCheck))) {
        continue;
      }
      String nodeRefText=getReferenceText(null,node);
      if (!(refText.equals(nodeRefText))) {
        continue;
      }
      if (result == null) {
        result=node;
      }
 else {
        return null;
      }
    }
  }
  return result;
}
