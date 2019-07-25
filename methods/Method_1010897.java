@Override public boolean contains(SNode node){
  return SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b204L,"jetbrains.mps.baseLanguage.structure.ConstructorDeclaration")) && classifiers.contains(SNodeOperations.getParent(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b204L,"jetbrains.mps.baseLanguage.structure.ConstructorDeclaration"))));
}
