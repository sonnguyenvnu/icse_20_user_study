public void execute(SNode node){
  SNode p=SNodeOperations.as(SNodeOperations.getParent(node),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc67c7f0L,"jetbrains.mps.baseLanguage.structure.LocalVariableDeclarationStatement"));
  if ((p != null)) {
    SNodeOperations.deleteNode(p);
  }
 else {
    SNodeOperations.deleteNode(node);
  }
}
