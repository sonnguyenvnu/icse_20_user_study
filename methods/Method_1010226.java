@Override public boolean contains(SNode node){
  if (SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,"jetbrains.mps.lang.core.structure.INamedConcept"))) {
    String name=SPropertyOperations.getString(SNodeOperations.cast(node,MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,"jetbrains.mps.lang.core.structure.INamedConcept")),MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"));
    return !(SetSequence.fromSet(filteredNames).contains(name)) && scope.contains(node);
  }
 else {
    return scope.contains(node);
  }
}
