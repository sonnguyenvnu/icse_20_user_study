@Override public SNode wrap(SNode sourceNode){
  SNode statement=SNodeFactoryOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc67c7f0L,"jetbrains.mps.baseLanguage.structure.LocalVariableDeclarationStatement"),null);
  SLinkOperations.setTarget(statement,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc67c7f0L,0xf8cc67c7f1L,"localVariableDeclaration"),sourceNode);
  return statement;
}
