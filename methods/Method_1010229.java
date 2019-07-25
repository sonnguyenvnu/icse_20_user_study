@Override public boolean contains(SNode node){
  if (myTargetConcept == null) {
    return false;
  }
  return SNodeUtil.isInstanceOf(node,myTargetConcept) && (!(myRootsOnly) || SNodeOperations.isRoot(node)) && SetSequence.fromSet(myModels).contains(node.getModel());
}
