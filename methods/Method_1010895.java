@Override public boolean contains(SNode node){
  if (!(SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")))) {
    return false;
  }
  if (super.contains(node)) {
    return true;
  }
  SNode root=Sequence.fromIterable(ClassifierResolveUtils.getPathToRoot(myClassifier)).last();
  if ((root != null) && (AttributeOperations.getAttribute(root,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x53f7c33f069862f2L,"jetbrains.mps.baseLanguage.structure.JavaImports"))) != null)) {
    return ClassifierResolveUtils.isImportedBy(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")),AttributeOperations.getAttribute(root,new IAttributeDescriptor.NodeAttribute(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x53f7c33f069862f2L,"jetbrains.mps.baseLanguage.structure.JavaImports"))));
  }
  return false;
}
