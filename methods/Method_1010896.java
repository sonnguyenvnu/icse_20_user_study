@Override public boolean contains(SNode node){
  if (!(SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration")))) {
    return false;
  }
  String name=SPropertyOperations.getString(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration")),MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"));
  return MapSequence.fromMap(nameToMethods).containsKey(name) && ListSequence.fromList(MapSequence.fromMap(nameToMethods).get(name)).contains(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration")));
}
