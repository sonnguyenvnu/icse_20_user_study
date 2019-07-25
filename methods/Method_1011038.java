public void execute(SNode node){
  int index=ListSequence.fromList(SLinkOperations.getChildren(((SNode)MakeStaticInitializerNotStatic_QuickFix.this.getField("containingClass")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,0x4a9a46de59132803L,"member"))).indexOf(((SNode)MakeStaticInitializerNotStatic_QuickFix.this.getField("staticInitializer")[0]));
  SNode initializer=SConceptOperations.createNewNode(MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x118f0b909f7L,"jetbrains.mps.baseLanguage.structure.InstanceInitializer"));
  ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(initializer,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x118f0b909f7L,0x118f0b95a3bL,"statementList")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).addSequence(ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(((SNode)MakeStaticInitializerNotStatic_QuickFix.this.getField("staticInitializer")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11c7538039dL,0x11c7538039eL,"statementList")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))));
  SNodeOperations.deleteNode(((SNode)MakeStaticInitializerNotStatic_QuickFix.this.getField("staticInitializer")[0]));
  if (index == -1) {
    ListSequence.fromList(SLinkOperations.getChildren(((SNode)MakeStaticInitializerNotStatic_QuickFix.this.getField("containingClass")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,0x4a9a46de59132803L,"member"))).addElement(initializer);
  }
 else {
    ListSequence.fromList(SLinkOperations.getChildren(((SNode)MakeStaticInitializerNotStatic_QuickFix.this.getField("containingClass")[0]),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,0x4a9a46de59132803L,"member"))).insertElement(index,initializer);
  }
}
