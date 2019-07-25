@Override public boolean contains(SNode node){
  if (!(SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,"jetbrains.mps.lang.core.structure.INamedConcept"))) || !(SNodeOperations.isInstanceOf(node,SNodeOperations.asSConcept(kindConcept)))) {
    return false;
  }
  if (scope.contains(node)) {
    return true;
  }
  if (SetSequence.fromSet(names).contains(node.getName())) {
    return false;
  }
  return parentScope.contains(node);
}
